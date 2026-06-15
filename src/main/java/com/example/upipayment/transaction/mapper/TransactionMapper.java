package com.example.upipayment.transaction.mapper;

import com.example.upipayment.common.enums.*;
import com.example.upipayment.common.util.IdGenerator;
import com.example.upipayment.payment.dto.request.PaymentInitiateRequestDTO;
import com.example.upipayment.payment.dto.response.PaymentResponseDTO;
import com.example.upipayment.transaction.dto.response.TransactionResponseDTO;
import com.example.upipayment.transaction.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public Transaction toEntity(PaymentInitiateRequestDTO request) {
        String txnId = request.getTransactionId() != null ? request.getTransactionId() : IdGenerator.transactionId();
        request.setTransactionId(txnId);
        return Transaction.builder()
                .transactionId(txnId)
                .senderUserId(request.getSenderUserId())
                .senderVpa(request.getSenderVpa())
                .receiverVpa(request.getReceiverVpa())
                .amount(request.getAmount())
                .providerType(request.getProviderType())
                .remarks(request.getRemarks())
                .status(TransactionStatus.INITIATED)
                .type(TransactionType.TRANSFER)
                .build();
    }

    public TransactionResponseDTO toResponseDTO(Transaction transaction) {
        return TransactionResponseDTO.builder()
                .transactionId(transaction.getTransactionId())
                .senderUserId(transaction.getSenderUserId())
                .receiverUserId(transaction.getReceiverUserId())
                .senderVpa(transaction.getSenderVpa())
                .receiverVpa(transaction.getReceiverVpa())
                .amount(transaction.getAmount())
                .status(transaction.getStatus())
                .type(transaction.getType())
                .providerType(transaction.getProviderType())
                .failureReason(transaction.getFailureReason())
                .createdAt(transaction.getCreatedAt())
                .updatedAt(transaction.getUpdatedAt())
                .build();
    }

    public PaymentResponseDTO toPaymentResponseDTO(Transaction transaction) {
        return PaymentResponseDTO.builder()
                .transactionId(transaction.getTransactionId())
                .senderVpa(transaction.getSenderVpa())
                .receiverVpa(transaction.getReceiverVpa())
                .amount(transaction.getAmount())
                .status(transaction.getStatus())
                .failureReason(transaction.getFailureReason())
                .message(transaction.getStatus() == TransactionStatus.SUCCESS ? "Payment successful" : "Payment status: " + transaction.getStatus())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}
