package com.example.upipayment.user.dto.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LoginResponseDTO {
    private String userId;
    private String mobileNumber;
    private String token;
    private String tokenType;
}