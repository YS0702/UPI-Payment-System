package com.example.upipayment.payment.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import com.example.upipayment.common.enums.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentInitiateRequestDTO {
    @NotBlank
    private String senderUserId;
    @NotBlank
    private String senderVpa;
    @NotBlank
    private String receiverVpa;
    @NotNull
    @DecimalMin("0.01")
    private BigDecimal amount;
    private String remarks;
    @NotNull
    private PaymentProviderType providerType;
    private String transactionId;
}
