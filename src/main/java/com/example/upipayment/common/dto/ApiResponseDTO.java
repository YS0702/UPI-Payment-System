package com.example.upipayment.common.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseDTO<T> {
    private boolean success;
    private String message;
    private T data;
    private String errorCode;
    private LocalDateTime timestamp;

    public static <T> ApiResponseDTO<T> success(String message, T data) {
        return ApiResponseDTO.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponseDTO<T> failure(String message, String errorCode) {
        return ApiResponseDTO.<T>builder()
                .success(false)
                .message(message)
                .errorCode(errorCode)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
