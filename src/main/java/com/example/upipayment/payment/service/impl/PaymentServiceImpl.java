package com.example.upipayment.payment.service.impl;

import com.example.upipayment.common.enums.*;
import com.example.upipayment.common.exception.BusinessException;
import com.example.upipayment.payment.dto.request.*;
import com.example.upipayment.payment.dto.response.*;
import com.example.upipayment.payment.service.PaymentService;
import com.example.upipayment.payment.strategy.PaymentStrategy;
import com.example.upipayment.transaction.entity.Transaction;
import com.example.upipayment.transaction.mapper.TransactionMapper;
import com.example.upipayment.transaction.service.TransactionService;
import com.example.upipayment.upi.service.UpiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final TransactionService transactionService;
    private final UpiService upiService;
    private final List<PaymentStrategy> paymentStrategies;
    private final TransactionMapper transactionMapper;

    @Override
    @Transactional
    public PaymentResponseDTO initiatePayment(PaymentInitiateRequestDTO request) {
        var txn = transactionService.createTransaction(request);
        try {
            PaymentValidationResponseDTO validation = validatePayment(
                    new PaymentValidationRequestDTO(
                            request.getSenderVpa(),
                            request.getReceiverVpa(),
                            request.getAmount())
            );
            if (!validation.isValid()) {
                transactionService.updateStatus(
                        txn.getTransactionId(),
                        TransactionStatus.FAILED,
                        validation.getMessage());
                return transactionMapper.toPaymentResponseDTO(
                        transactionService.getTransactionEntity(txn.getTransactionId()));
            }
            transactionService.updateStatus(
                    txn.getTransactionId(), TransactionStatus.PROCESSING, null);
            PaymentStrategy strategy = resolveStrategy(request.getProviderType());
            strategy.pay(request);
            transactionService.updateStatus(
                    txn.getTransactionId(),
                    TransactionStatus.SUCCESS,
                    null);
            Transaction success = transactionService.getTransactionEntity(
                    txn.getTransactionId());
            return transactionMapper.toPaymentResponseDTO(success);
        } catch (Exception ex) {
            transactionService.updateStatus(
                    txn.getTransactionId(),
                    TransactionStatus.FAILED,
                    ex.getMessage());
            throw ex;
        }
    }

    @Override
    public PaymentValidationResponseDTO validatePayment(PaymentValidationRequestDTO request) {
        return upiService.validatePaymentParties(
                request.getSenderVpa(),
                request.getReceiverVpa(),
                request.getAmount());
    }

    @Override
    public PaymentResponseDTO getPaymentStatus(String transactionId) {
        return transactionMapper.toPaymentResponseDTO(
                transactionService.getTransactionEntity(transactionId));
    }

    private PaymentStrategy resolveStrategy(PaymentProviderType providerType) {
        return paymentStrategies.stream()
                .filter(strategy -> strategy.supports() == providerType)
                .findFirst()
                .orElseThrow(() -> new BusinessException("Unsupported payment provider"));
    }
}
