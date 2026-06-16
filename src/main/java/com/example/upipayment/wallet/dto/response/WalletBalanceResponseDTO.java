package com.example.upipayment.wallet.dto.response;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.example.upipayment.common.enums.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WalletBalanceResponseDTO {
    private String walletId;
    private String userId;
    private BigDecimal balance;
    private WalletStatus status;
}