package com.example.upipayment.upi.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import com.example.upipayment.common.enums.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpiValidationRequestDTO {
    @NotBlank
    private String vpa;
}