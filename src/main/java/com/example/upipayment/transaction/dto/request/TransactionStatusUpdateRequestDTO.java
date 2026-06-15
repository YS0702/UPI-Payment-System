package com.example.upipayment.transaction.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import com.example.upipayment.common.enums.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TransactionStatusUpdateRequestDTO {
    @NotNull
    private TransactionStatus status;
    private String failureReason;
}
