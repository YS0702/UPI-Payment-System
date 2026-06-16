package com.example.upipayment.wallet.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import com.example.upipayment.common.enums.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WalletBalanceRequestDTO {
    private String userId;
    private String walletId;
}