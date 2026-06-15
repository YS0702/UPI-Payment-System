package com.example.upipayment.transaction.repository;

import com.example.upipayment.transaction.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByTransactionId(String transactionId);
    Page<Transaction> findBySenderUserIdOrReceiverUserId(String senderUserId, String receiverUserId, Pageable pageable);
}
