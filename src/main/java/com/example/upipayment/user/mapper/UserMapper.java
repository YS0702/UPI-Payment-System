package com.example.upipayment.user.mapper;

import com.example.upipayment.common.util.IdGenerator;
import com.example.upipayment.user.dto.request.UserRegisterRequestDTO;
import com.example.upipayment.user.dto.response.*;
import com.example.upipayment.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRegisterRequestDTO request) {
        return User.builder()
                .userId(IdGenerator.userId())
                .fullName(request.getFullName())
                .mobileNumber(request.getMobileNumber())
                .email(request.getEmail())
                .upiId(request.getUpiId())
                .passwordHash(request.getPassword())
                .build();
    }

    public UserResponseDTO toResponseDTO(User user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .mobileNumber(user.getMobileNumber())
                .email(user.getEmail())
                .upiId(user.getUpiId())
                .walletId(user.getWallet() != null ? user.getWallet().getWalletId() : null)
                .build();
    }

    public LoginResponseDTO toLoginResponseDTO(User user, String token) {
        return LoginResponseDTO.builder()
                .userId(user.getUserId())
                .mobileNumber(user.getMobileNumber())
                .token(token)
                .tokenType("Bearer")
                .build();
    }
}