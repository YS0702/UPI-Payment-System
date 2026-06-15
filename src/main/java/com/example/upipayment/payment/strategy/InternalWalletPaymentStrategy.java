package com.example.upipayment.payment.strategy;

import com.example.upipayment.common.enums.*;
import com.example.upipayment.payment.dto.request.PaymentInitiateRequestDTO;
import com.example.upipayment.payment.dto.response.PaymentResponseDTO;
import com.example.upipayment.user.entity.User;
import com.example.upipayment.user.service.UserService;
import com.example.upipayment.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InternalWalletPaymentStrategy implements PaymentStrategy {
    private final UserService userService;
    private final WalletService walletService;

    @Override
    public PaymentResponseDTO pay(PaymentInitiateRequestDTO request) {
        User sender = userService.getUserByVpa(request.getSenderVpa());
        User receiver = userService.getUserByVpa(request.getReceiverVpa());
        walletService.debitWallet(sender.getUserId(), request.getAmount(), request.getTransactionId());
        walletService.creditWallet(receiver.getUserId(), request.getAmount(), request.getTransactionId());
        return PaymentResponseDTO.builder()
                .transactionId(request.getTransactionId())
                .senderVpa(request.getSenderVpa())
                .receiverVpa(request.getReceiverVpa())
                .amount(request.getAmount())
                .status(TransactionStatus.SUCCESS)
                .message("Internal wallet payment successful")
                .build();
    }

    @Override
    public PaymentProviderType supports() {
        return PaymentProviderType.INTERNAL_WALLET;
    }
}
