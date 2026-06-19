package com.example.upipayment.user.service;

import com.example.upipayment.user.dto.request.*;
import com.example.upipayment.user.dto.response.*;
import com.example.upipayment.user.entity.User;

public interface UserService {
    UserResponseDTO register(UserRegisterRequestDTO request);
    LoginResponseDTO authenticateUser(LoginRequestDTO request);
    UserResponseDTO linkBankAccount(String userId, LinkBankAccountRequestDTO request);
    UserResponseDTO getUserById(String userId);
    User getUserEntityById(String userId);
    User getUserByVpa(String vpa);
}