package com.example.upipayment.user.service.impl;

import com.example.upipayment.common.enums.WalletStatus;
import com.example.upipayment.common.exception.*;
import com.example.upipayment.common.util.IdGenerator;
import com.example.upipayment.user.dto.request.*;
import com.example.upipayment.user.dto.response.*;
import com.example.upipayment.user.entity.User;
import com.example.upipayment.user.mapper.UserMapper;
import com.example.upipayment.user.repository.UserRepository;
import com.example.upipayment.user.service.UserService;
import com.example.upipayment.wallet.entity.Wallet;
import com.example.upipayment.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserResponseDTO register(UserRegisterRequestDTO request) {
        if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
            throw new DuplicateResourceException("Mobile number already registered");
        }
        if (userRepository.existsByUpiId(request.getUpiId())) {
            throw new DuplicateResourceException("UPI ID already registered");
        }
        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);
        Wallet wallet = Wallet.builder()
                .walletId(IdGenerator.walletId())
                .user(savedUser)
                .balance(BigDecimal.ZERO)
                .status(WalletStatus.ACTIVE)
                .build();
        Wallet savedWallet = walletRepository.save(wallet);
        savedUser.setWallet(savedWallet);
        return userMapper.toResponseDTO(savedUser);
    }

    @Override
    public LoginResponseDTO authenticateUser(LoginRequestDTO request) {
        User user = userRepository.findByMobileNumber(request.getMobileNumber())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!user.getPasswordHash().equals(request.getPassword())) {
            throw new BusinessException("Invalid credentials");
        }
        return userMapper.toLoginResponseDTO(user, "dummy-jwt-token");
    }

    @Override
    @Transactional
    public UserResponseDTO linkBankAccount(String userId, LinkBankAccountRequestDTO request) {
        User user = getUserEntityById(userId);
        if (!user.getUpiId().equals(request.getUpiId()) && userRepository.existsByUpiId(request.getUpiId())) {
            throw new DuplicateResourceException("UPI ID already linked with another user");
        }
        user.setUpiId(request.getUpiId());
        return userMapper.toResponseDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO getUserById(String userId) {
        return userMapper.toResponseDTO(getUserEntityById(userId));
    }

    @Override
    public User getUserEntityById(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User getUserByVpa(String vpa) {
        return userRepository.findByUpiId(vpa)
                .orElseThrow(() -> new ResourceNotFoundException("VPA not found"));
    }
}
