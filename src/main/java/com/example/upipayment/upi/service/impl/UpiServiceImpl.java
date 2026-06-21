package com.example.upipayment.upi.service.impl;

import com.example.upipayment.common.enums.PaymentProviderType;
import com.example.upipayment.common.util.VpaValidator;
import com.example.upipayment.payment.dto.response.PaymentValidationResponseDTO;
import com.example.upipayment.upi.dto.response.UpiValidationResponseDTO;
import com.example.upipayment.upi.mapper.UpiMapper;
import com.example.upipayment.upi.service.UpiService;
import com.example.upipayment.user.repository.UserRepository;
import com.example.upipayment.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UpiServiceImpl implements UpiService {
    private final UserRepository userRepository;
    private final WalletService walletService;
    private final UpiMapper upiMapper;

    @Override
    public UpiValidationResponseDTO validateVPA(String vpa) {
        boolean valid = VpaValidator.isValid(vpa) && userRepository.findByUpiId(vpa).isPresent();
        return upiMapper.toResponse(vpa, valid, routeTransaction(vpa), valid ? "Valid VPA" : "Invalid VPA");
    }

    @Override
    public PaymentValidationResponseDTO validatePaymentParties(String senderVpa, String receiverVpa, BigDecimal amount) {
        boolean senderValid = validateVPA(senderVpa).isValid();
        boolean receiverValid = validateVPA(receiverVpa).isValid();
        boolean notSame = senderVpa != null && !senderVpa.equals(receiverVpa);
        boolean balanceAvailable = false;
        if (senderValid) {
            String senderUserId = userRepository.findByUpiId(senderVpa).get().getUserId();
            balanceAvailable = walletService.hasSufficientBalance(senderUserId, amount);
        }
        boolean valid = senderValid && receiverValid && notSame && balanceAvailable;
        return PaymentValidationResponseDTO.builder()
                .valid(valid)
                .senderValid(senderValid)
                .receiverValid(receiverValid)
                .balanceAvailable(balanceAvailable)
                .message(valid ? "Payment validation successful" : "Payment validation failed")
                .build();
    }

    @Override
    public PaymentProviderType routeTransaction(String receiverVpa) {
        return PaymentProviderType.INTERNAL_WALLET;
    }
}
