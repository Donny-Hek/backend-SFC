package com.example.backendvkr.controller;

import com.example.backendvkr.dto.RegisterDto;
import com.example.backendvkr.model.Authoriz;
import com.example.backendvkr.service.AuthorizService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/auth")
public class AuthorizController {
    private AuthorizService registrationService;

    public AuthorizController(AuthorizService authorizService) {
        this.registrationService = authorizService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterDto request) {
        registrationService.register(request);
        return "User registered successfully";
    }

}
