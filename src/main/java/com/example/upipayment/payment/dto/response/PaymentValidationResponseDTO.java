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
public class PaymentValidationResponseDTO {
    private boolean valid;
    private boolean senderValid;
    private boolean receiverValid;
    private boolean balanceAvailable;
    private String message;
}
