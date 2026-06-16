package com.example.upipayment.wallet.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import com.example.upipayment.common.enums.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WalletDebitRequestDTO {
    @NotNull @DecimalMin("0.01") private BigDecimal amount;
    private String transactionId;
    private String remarks;
}
