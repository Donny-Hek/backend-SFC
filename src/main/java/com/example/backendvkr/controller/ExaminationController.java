package com.example.backendvkr.controller;

import com.example.backendvkr.service.ExaminationService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/examinations")
@RequiredArgsConstructor
//@NoArgsConstructor
public class ExaminationController {
    private final ExaminationService examinationService;

    @PostMapping("/upload/{subjectId}")
    public void uploadExaminationFiles(@RequestHeader("Authorization") String token,
                                                    @RequestParam("excelFile") MultipartFile excelFile,
                                                    @RequestParam("photos") MultipartFile[] photos,
                                                    @PathVariable Integer subjectId) throws IOException {
        examinationService.uploadExaminationFiles(token.substring(7, token.length()),excelFile, photos, subjectId);
    }
//ResponseEntity<?>
    @GetMapping("/info/{userId}")
    public Integer[] accountInfo(@PathVariable Integer userId) {
        return examinationService.accountInfo(userId);
    }

    @GetMapping("/last/{userId}")
    public ResponseEntity<?> accountLastExamination(@PathVariable Integer userId) {
        return examinationService.accountLastExamination(userId);
    }

    @GetMapping("/list")
    public Map<Integer, String> subjectList() {
        return examinationService.subjectList();
    }

    @GetMapping("/schema/{subjectId}")
    public ResponseEntity<?> getAnswerSchema(@PathVariable Integer subjectId) {
        return ResponseEntity.ok("");
//        return examinationService.getAnswerSchema(subjectId);
    }
}
