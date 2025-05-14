package com.example.backendvkr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenRefreshDto {
    private String accessToken;
    private String refreshToken;
    private final String tokenType = "Bearer";
}
