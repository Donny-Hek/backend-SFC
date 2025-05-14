package com.example.backendvkr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {
    private String accessToken;
    private String type = "Bearer";
    private String refreshToken;
//    Личные данные
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String status;

}
