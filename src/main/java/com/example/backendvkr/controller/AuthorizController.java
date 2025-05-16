package com.example.backendvkr.controller;

import com.example.backendvkr.dto.LoginDto;
import com.example.backendvkr.dto.RegisterDto;
import com.example.backendvkr.service.AuthorizService;
import com.example.backendvkr.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthorizController {
    private final AuthorizService registrationService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        return registrationService.login(loginDto);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto request) {
        return registrationService.register(request);
    }
//
//    @PostMapping("/refreshtoken")
//    public ResponseEntity<?> refreshtoken(@Validated @RequestBody TokenRefreshDto request) {
//        String requestRefreshToken = request.getRefreshToken();
//        return refreshTokenService.refreshtoken(requestRefreshToken);
//    }
}
