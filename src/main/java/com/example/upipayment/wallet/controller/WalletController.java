package com.example.upipayment.wallet.controller;

import com.example.upipayment.common.dto.ApiResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.upipayment.wallet.dto.request.*;
import com.example.upipayment.wallet.dto.response.*;
import com.example.upipayment.wallet.service.WalletService;

@RestController
@RequestMapping("/api/v1/wallets")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @GetMapping("/{userId}/balance")
    public ResponseEntity<ApiResponseDTO<WalletBalanceResponseDTO>> getBalance(@PathVariable String userId) {
        return ResponseEntity.ok(ApiResponseDTO.success("Balance fetched", walletService.getBalance(userId)));
    }

    @PostMapping("/{userId}/credit")
    public ResponseEntity<ApiResponseDTO<WalletResponseDTO>> creditWallet(@PathVariable String userId, @Valid @RequestBody WalletCreditRequestDTO request) {
        return ResponseEntity.ok(ApiResponseDTO.success("Wallet credited", walletService.creditWallet(userId, request.getAmount(), request.getTransactionId())));
    }

    @PostMapping("/{userId}/debit")
    public ResponseEntity<ApiResponseDTO<WalletResponseDTO>> debitWallet(@PathVariable String userId, @Valid @RequestBody WalletDebitRequestDTO request) {
        return ResponseEntity.ok(ApiResponseDTO.success("Wallet debited", walletService.debitWallet(userId, request.getAmount(), request.getTransactionId())));
    }
}


