package com.example.backendvkr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String status; //учитель, ученик, прочее
}
