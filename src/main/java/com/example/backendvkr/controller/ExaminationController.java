package com.example.backendvkr.controller;

import com.example.backendvkr.dto.LoginDto;
import com.example.backendvkr.service.ExaminaitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/examinations")
@RequiredArgsConstructor
public class ExaminationController {
private final ExaminaitionService examinaitionService;
    @PostMapping("/upload")
    public ResponseEntity<?> uploadExaminaionFiles(@RequestBody LoginDto loginDto) {
//        return examinaitionService.uploadExaminaionFiles();
    }
    @GetMapping("/info")
    public ResponseEntity<?> accountInfo (@RequestBody LoginDto loginDto) {
//        return examinaitionService.accountInfo();
    }
}
