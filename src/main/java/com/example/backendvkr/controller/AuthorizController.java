package com.example.backendvkr.controller;

import com.example.backendvkr.dto.RegisterDto;
import com.example.backendvkr.model.Authoriz;
import com.example.backendvkr.service.AuthorizService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizController {
    private AuthorizService registrationService;

    public AuthorizController(AuthorizService authorizService) {
        this.registrationService = authorizService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterDto request) {
//        registrationService.registerUser(request);
        return "User registered successfully";
    }

}
