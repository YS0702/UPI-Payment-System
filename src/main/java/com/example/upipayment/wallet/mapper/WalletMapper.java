package com.example.upipayment.wallet.mapper;

import com.example.upipayment.wallet.dto.response.*;
import com.example.upipayment.wallet.entity.Wallet;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {
    public WalletResponseDTO toResponseDTO(Wallet wallet) {
        return WalletResponseDTO.builder()
                .walletId(wallet.getWalletId())
                .userId(wallet.getUser().getUserId())
                .balance(wallet.getBalance())
                .status(wallet.getStatus())
                .message("Wallet operation successful")
                .build();
    }

    public WalletBalanceResponseDTO toBalanceResponseDTO(Wallet wallet) {
        return WalletBalanceResponseDTO.builder()
                .walletId(wallet.getWalletId())
                .userId(wallet.getUser().getUserId())
                .balance(wallet.getBalance())
                .status(wallet.getStatus())
                .build();
    }
}
