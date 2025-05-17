package com.example.backendvkr.service;

import com.example.backendvkr.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class ExaminaitionService {
    public ResponseEntity<?> uploadExaminaionFiles(@RequestBody LoginDto loginDto) {
//        return registrationService.login(loginDto);
    }
    public ResponseEntity<?> accountInfo (@RequestBody LoginDto loginDto) {
//        return registrationService.login(loginDto);
    }
}
