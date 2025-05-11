package com.example.backendvkr.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String status; //учитель, ученик, прочее
}
