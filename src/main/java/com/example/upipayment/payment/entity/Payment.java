package com.example.upipayment.payment.entity;

import com.example.upipayment.common.enums.PaymentProviderType;
import com.example.upipayment.common.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")   // Ã¢Å“â€¦ REMOVED unique constraint for now
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ã¢Å“â€¦ IMPORTANT: column name must match exactly
    @Column(name = "transaction_id")
    private String transactionId;

    private BigDecimal amount;

    // Ã¢Å“â€¦ REQUIRED FOR ENUMS
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    // Ã¢Å“â€¦ REQUIRED FOR ENUMS
    @Enumerated(EnumType.STRING)
    private PaymentProviderType providerType;

    private String remarks;
    private String failureReason;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
}