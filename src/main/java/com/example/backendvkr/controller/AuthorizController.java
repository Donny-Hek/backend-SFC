package com.example.backendvkr.controller;

import com.example.backendvkr.dto.AuthResponseDto;
import com.example.backendvkr.dto.LoginDto;
import com.example.backendvkr.dto.RegisterDto;
import com.example.backendvkr.dto.TokenRefreshDto;
import com.example.backendvkr.model.Authoriz;
import com.example.backendvkr.model.RefreshToken;
import com.example.backendvkr.sequrity.TokenRefreshException;
import com.example.backendvkr.service.AuthorizService;
import com.example.backendvkr.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
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

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Validated @RequestBody TokenRefreshDto request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}
