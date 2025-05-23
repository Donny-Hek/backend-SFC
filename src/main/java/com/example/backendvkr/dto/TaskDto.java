package com.example.backendvkr.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDto {
    private String question;
    private String answer;
    private int score;
}
