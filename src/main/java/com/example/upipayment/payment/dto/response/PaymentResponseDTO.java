package com.example.upipayment.payment.dto.response;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.example.upipayment.common.enums.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseDTO {
    private String transactionId;
    private String senderVpa;
    private String receiverVpa;
    private BigDecimal amount;
    private TransactionStatus status;
    private String message;
    private String failureReason;
    private LocalDateTime createdAt;
}
