package com.example.backendvkr.controller;

import com.example.backendvkr.dto.LoginDto;
import com.example.backendvkr.service.ExaminaitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/examinations")
@RequiredArgsConstructor
public class ExaminationController {
private final ExaminaitionService examinaitionService;
    @PostMapping("/upload")
    public ResponseEntity<?> uploadExaminaionFiles(@RequestParam("excelFile") MultipartFile excelFile,
                                                   @RequestParam("photos") MultipartFile[] photos) {
        return examinaitionService.uploadExaminaionFiles(excelFile,photos);
    }
    @GetMapping("/info/{id}")
    public Integer[] accountInfo (@PathVariable Integer id) {
        return examinaitionService.accountInfo(id);
    }
    @GetMapping("/last/{id}")
    public ResponseEntity<?> accountLastExamination (@PathVariable Integer id) {
        return examinaitionService.accountLastExamination(id);
    }
    @GetMapping("/list")
    public String[] subjectList(){
        return examinaitionService.subjectList();
    }

    @GetMapping("/schema/{subjectId}")
    public ResponseEntity<?> getAnswerSchema (@PathVariable Integer subjectId) {
        return examinaitionService.getAnswerSchema(subjectId);
    }
}
