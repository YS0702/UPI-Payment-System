package com.example.upipayment.payment.service;

import com.example.upipayment.payment.dto.request.*;
import com.example.upipayment.payment.dto.response.*;

public interface PaymentService {
    PaymentResponseDTO initiatePayment(PaymentInitiateRequestDTO request);
    PaymentValidationResponseDTO validatePayment(PaymentValidationRequestDTO request);
    PaymentResponseDTO getPaymentStatus(String transactionId);
}
