package com.example.upipayment.transaction.controller;

import com.example.upipayment.common.dto.ApiResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.upipayment.transaction.dto.request.TransactionStatusUpdateRequestDTO;
import com.example.upipayment.transaction.dto.response.TransactionResponseDTO;
import com.example.upipayment.transaction.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/{transactionId}")
    public ResponseEntity<ApiResponseDTO<TransactionResponseDTO>> getTransaction(@PathVariable String transactionId) {
        return ResponseEntity.ok(ApiResponseDTO.success(
                "Transaction fetched",
                transactionService.getTransaction(transactionId)));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponseDTO<Page<TransactionResponseDTO>>> getUserTransactions(
            @PathVariable String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponseDTO.success(
                "Transactions fetched",
                transactionService.getUserTransactions(userId, PageRequest.of(page, size))));
    }

    @PatchMapping("/{transactionId}/status")
    public ResponseEntity<ApiResponseDTO<TransactionResponseDTO>> updateStatus(@PathVariable String transactionId, @Valid @RequestBody TransactionStatusUpdateRequestDTO request) {
        return ResponseEntity.ok(ApiResponseDTO.success(
                "Transaction status updated",
                transactionService.updateStatus(transactionId, request.getStatus(), request.getFailureReason())));
    }
}
