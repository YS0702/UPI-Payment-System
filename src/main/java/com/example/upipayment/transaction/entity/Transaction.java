package com.example.upipayment.transaction.entity;

import com.example.upipayment.common.enums.PaymentProviderType;
import com.example.upipayment.common.enums.TransactionStatus;
import com.example.upipayment.common.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "transactions",
        uniqueConstraints = @UniqueConstraint(columnNames = "transactionId")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionId;

    private String senderUserId;
    private String receiverUserId;

    private String senderVpa;
    private String receiverVpa;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    private PaymentProviderType providerType;

    private String remarks;

    private String failureReason;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Automatically set timestamps when inserting
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // Automatically update timestamp when updating
    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}