package com.example.backendvkr.repository;

import com.example.backendvkr.model.Examination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExaminationRepository extends JpaRepository<Examination, Integer> {
    Integer countExaminationByUser_IdAndCreatedAt_Month(Integer user_id, short createdAt_month);
}
