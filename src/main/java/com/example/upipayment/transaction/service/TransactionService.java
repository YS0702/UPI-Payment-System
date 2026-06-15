package com.example.upipayment.transaction.service;

import com.example.upipayment.common.enums.TransactionStatus;
import com.example.upipayment.payment.dto.request.PaymentInitiateRequestDTO;
import com.example.upipayment.transaction.dto.response.TransactionResponseDTO;
import com.example.upipayment.transaction.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {
    TransactionResponseDTO createTransaction(PaymentInitiateRequestDTO request);
    TransactionResponseDTO updateStatus(String transactionId, TransactionStatus status, String failureReason);
    TransactionResponseDTO getTransaction(String transactionId);
    Page<TransactionResponseDTO> getUserTransactions(String userId, Pageable pageable);
    Transaction getTransactionEntity(String transactionId);
}
