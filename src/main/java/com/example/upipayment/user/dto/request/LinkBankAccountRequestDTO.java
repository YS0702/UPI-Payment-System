package com.example.upipayment.user.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LinkBankAccountRequestDTO {
    @NotBlank private String bankName;
    @NotBlank private String accountNumberMasked;
    @NotBlank private String ifsc;
    @NotBlank private String upiId;
}