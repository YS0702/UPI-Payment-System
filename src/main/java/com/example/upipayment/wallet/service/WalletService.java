package com.example.upipayment.wallet.service;

import com.example.upipayment.wallet.dto.response.*;
import com.example.upipayment.wallet.entity.Wallet;
import java.math.BigDecimal;

public interface WalletService {
    WalletBalanceResponseDTO getBalance(String userId);
    WalletResponseDTO debitWallet(String userId, BigDecimal amount, String transactionId);
    WalletResponseDTO creditWallet(String userId, BigDecimal amount, String transactionId);
    Wallet getWalletEntityByUserId(String userId);
    boolean hasSufficientBalance(String userId, BigDecimal amount);
}
