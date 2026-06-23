package com.example.upipayment.user.dto.response;

import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserResponseDTO {
    private String userId;
    private String fullName;
    private String mobileNumber;
    private String email;
    private String upiId;
    private String walletId;
}