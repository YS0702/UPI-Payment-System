package com.example.upipayment.payment.controller;

import com.example.upipayment.common.dto.ApiResponseDTO;
import com.example.upipayment.payment.dto.request.PaymentInitiateRequestDTO;
import com.example.upipayment.payment.dto.request.PaymentValidationRequestDTO;
import com.example.upipayment.payment.dto.response.PaymentResponseDTO;
import com.example.upipayment.payment.dto.response.PaymentValidationResponseDTO;
import com.example.upipayment.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/initiate")
    public ResponseEntity<ApiResponseDTO<PaymentResponseDTO>> initiatePayment(
            @Valid @RequestBody PaymentInitiateRequestDTO request
            ) {
        return ResponseEntity.ok(ApiResponseDTO.success("Payment processed", paymentService.initiatePayment(request));
    }

    @PostMapping("/validate")
    public ResponseEntity<ApiResponseDTO<PaymentValidationResponseDTO>> validatePayment(
            @Valid @RequestBody PaymentValidationRequestDTO request
            ) {
        return ResponseEntity.ok(ApiResponseDTO.success("Payment validated", paymentService.validatePayment(request));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<ApiResponseDTO<PaymentResponseDTO>> getPaymentStatus(
            @PathVariable String transactionId
    ) {
        return ResponseEntity.ok(ApiResponseDTO.success("Payment status fetched", paymentService.getPaymentStatus(transactionId));
    }
}
