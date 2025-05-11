package com.example.backendvkr.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;
    private String role;
//    может потребоваться изменение. Зачем роль?
}
