package com.example.upipayment.upi.controller;

import com.example.upipayment.common.dto.ApiResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.upipayment.upi.dto.response.UpiValidationResponseDTO;
import com.example.upipayment.upi.service.UpiService;

@RestController
@RequestMapping("/api/v1/upi")
@RequiredArgsConstructor
public class UpiController {
    private final UpiService upiService;

    @GetMapping("/validate/{vpa}")
    public ResponseEntity<ApiResponseDTO<UpiValidationResponseDTO>> validateVpa(@PathVariable String vpa) {
        return ResponseEntity.ok(ApiResponseDTO.success("VPA validation completed", upiService.validateVPA(vpa)));
    }

    @GetMapping("/route/{vpa}")
    public ResponseEntity<ApiResponseDTO<UpiValidationResponseDTO>> routeTransaction(@PathVariable String vpa) {
        return ResponseEntity.ok(ApiResponseDTO.success("Route fetched", upiService.validateVPA(vpa)));
    }
}
