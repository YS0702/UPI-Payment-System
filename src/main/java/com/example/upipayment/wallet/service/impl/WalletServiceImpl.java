package com.example.upipayment.wallet.service.impl;

import com.example.upipayment.common.enums.WalletStatus;
import com.example.upipayment.common.exception.*;
import com.example.upipayment.wallet.dto.response.*;
import com.example.upipayment.wallet.entity.Wallet;
import com.example.upipayment.wallet.mapper.WalletMapper;
import com.example.upipayment.wallet.repository.WalletRepository;
import com.example.upipayment.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    @Override
    public WalletBalanceResponseDTO getBalance(String userId) {
        return walletMapper.toBalanceResponseDTO(getWalletEntityByUserId(userId));
    }

    @Override
    @Transactional
    public WalletResponseDTO debitWallet(String userId, BigDecimal amount, String transactionId) {
        Wallet wallet = getWalletEntityByUserId(userId);
        validateActive(wallet);
        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient wallet balance");
        }
        wallet.setBalance(wallet.getBalance().subtract(amount));
        return walletMapper.toResponseDTO(walletRepository.save(wallet));
    }

    @Override
    @Transactional
    public WalletResponseDTO creditWallet(String userId, BigDecimal amount, String transactionId) {
        Wallet wallet = getWalletEntityByUserId(userId);
        validateActive(wallet);
        wallet.setBalance(wallet.getBalance().add(amount));
        return walletMapper.toResponseDTO(walletRepository.save(wallet));
    }

    @Override
    public Wallet getWalletEntityByUserId(String userId) {
        return walletRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
    }

    @Override
    public boolean hasSufficientBalance(String userId, BigDecimal amount) {
        return getWalletEntityByUserId(userId).getBalance().compareTo(amount) >= 0;
    }

    private void validateActive(Wallet wallet) {
        if (wallet.getStatus() != WalletStatus.ACTIVE) {
            throw new BusinessException("Wallet is not active");
        }
    }
}