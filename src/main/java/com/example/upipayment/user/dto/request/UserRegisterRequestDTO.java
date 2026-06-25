package com.example.upipayment.user.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserRegisterRequestDTO {
    @NotBlank private String fullName;
    @NotBlank private String mobileNumber;
    private String email;
    @NotBlank private String upiId;
    @NotBlank private String password;
}