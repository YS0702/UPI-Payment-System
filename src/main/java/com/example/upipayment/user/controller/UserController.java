package com.example.upipayment.user.controller;

import com.example.upipayment.common.dto.ApiResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.upipayment.user.dto.request.*;
import com.example.upipayment.user.dto.response.*;
import com.example.upipayment.user.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> registerUser(@Valid @RequestBody UserRegisterRequestDTO request) {
        return ResponseEntity.ok(ApiResponseDTO.success("User registered", userService.register(request)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO<LoginResponseDTO>> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(ApiResponseDTO.success("Login successful", userService.authenticateUser(request)));
    }

    @PostMapping("/{userId}/link-bank")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> linkBankAccount(@PathVariable String userId, @Valid @RequestBody LinkBankAccountRequestDTO request) {
        return ResponseEntity.ok(ApiResponseDTO.success("Bank account linked", userService.linkBankAccount(userId, request)));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> getUserById(@PathVariable String userId) {
        return ResponseEntity.ok(ApiResponseDTO.success("User fetched", userService.getUserById(userId)));
    }
}
