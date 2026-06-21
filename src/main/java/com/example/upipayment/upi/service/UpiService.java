package com.example.upipayment.upi.service;

import com.example.upipayment.common.enums.PaymentProviderType;
import com.example.upipayment.payment.dto.response.PaymentValidationResponseDTO;
import com.example.upipayment.upi.dto.response.UpiValidationResponseDTO;
import java.math.BigDecimal;

public interface UpiService {
    UpiValidationResponseDTO validateVPA(String vpa);
    PaymentValidationResponseDTO validatePaymentParties(String senderVpa, String receiverVpa, BigDecimal amount);
    PaymentProviderType routeTransaction(String receiverVpa);
}
