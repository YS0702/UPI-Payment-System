package com.example.upipayment.transaction.service.impl;

import com.example.upipayment.common.enums.TransactionStatus;
import com.example.upipayment.common.exception.ResourceNotFoundException;
import com.example.upipayment.payment.dto.request.PaymentInitiateRequestDTO;
import com.example.upipayment.transaction.dto.response.TransactionResponseDTO;
import com.example.upipayment.transaction.entity.Transaction;
import com.example.upipayment.transaction.mapper.TransactionMapper;
import com.example.upipayment.transaction.repository.TransactionRepository;
import com.example.upipayment.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    @Transactional
    public TransactionResponseDTO createTransaction(PaymentInitiateRequestDTO request) {
        return transactionMapper.toResponseDTO(transactionRepository.save(transactionMapper.toEntity(request)));
    }

    @Override
    @Transactional
    public TransactionResponseDTO updateStatus(String transactionId, TransactionStatus status, String failureReason) {
        Transaction transaction = getTransactionEntity(transactionId);
        transaction.setStatus(status);
        transaction.setFailureReason(failureReason);
        return transactionMapper.toResponseDTO(transactionRepository.save(transaction));
    }

    @Override
    public TransactionResponseDTO getTransaction(String transactionId) {
        return transactionMapper.toResponseDTO(getTransactionEntity(transactionId));
    }

    @Override
    public Page<TransactionResponseDTO> getUserTransactions(String userId, Pageable pageable) {
        return transactionRepository.findBySenderUserIdOrReceiverUserId(userId, userId, pageable)
                .map(transactionMapper::toResponseDTO);
    }

    @Override
    public Transaction getTransactionEntity(String transactionId) {
        return transactionRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
    }
}
