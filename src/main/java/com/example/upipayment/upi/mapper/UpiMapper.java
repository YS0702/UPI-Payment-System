package com.example.upipayment.upi.mapper;

import com.example.upipayment.common.enums.PaymentProviderType;
import com.example.upipayment.upi.dto.response.UpiValidationResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UpiMapper {
    public UpiValidationResponseDTO toResponse(String vpa, boolean valid, PaymentProviderType providerType, String message) {
        return UpiValidationResponseDTO.builder()
                .vpa(vpa)
                .valid(valid)
                .providerType(providerType)
                .message(message)
                .build();
    }
}
