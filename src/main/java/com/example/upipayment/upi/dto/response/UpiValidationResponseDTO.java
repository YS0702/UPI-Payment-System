package com.example.upipayment.upi.dto.response;

import lombok.*;
import com.example.upipayment.common.enums.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UpiValidationResponseDTO {
    private String vpa;
    private boolean valid;
    private PaymentProviderType providerType;
    private String message;
}
