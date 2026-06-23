package com.example.upipayment.user.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LoginRequestDTO {
    @NotBlank private String mobileNumber;
    @NotBlank private String password;
}