package com.example.backendvkr.repository;

import com.example.backendvkr.model.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Integer> {

    @Query("SELECT COUNT(e) FROM Examination e WHERE e.user.id = :userId AND EXTRACT(MONTH FROM e.createdAt) = :month")
    Integer countExaminationByUser_IdAndCreatedAt_Month(@Param("userId") Integer user_id, @Param("month") int createdAt_month);
}
