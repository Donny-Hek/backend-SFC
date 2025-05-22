package com.example.backendvkr.repository;

import com.example.backendvkr.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> getSubjectsBySubjType(String subjType);
}
