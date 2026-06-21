package com.example.upipayment.payment.strategy;

import com.example.upipayment.common.enums.PaymentProviderType;
import com.example.upipayment.payment.dto.request.PaymentInitiateRequestDTO;
import com.example.upipayment.payment.dto.response.PaymentResponseDTO;

public interface PaymentStrategy {
    PaymentResponseDTO pay(PaymentInitiateRequestDTO request);
    PaymentProviderType supports();
}
