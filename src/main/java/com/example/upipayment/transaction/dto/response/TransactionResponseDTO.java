package com.example.upipayment.transaction.dto.response;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.example.upipayment.common.enums.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TransactionResponseDTO {
    private String transactionId;
    private String senderUserId;
    private String receiverUserId;
    private String senderVpa;
    private String receiverVpa;
    private BigDecimal amount;
    private TransactionStatus status;
    private TransactionType type;
    private PaymentProviderType providerType;
    private String failureReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
