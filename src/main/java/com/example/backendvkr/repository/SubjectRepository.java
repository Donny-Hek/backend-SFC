package com.example.backendvkr.repository;

import com.example.backendvkr.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> getSubjectsBySubjType(String subjType);

    List<Subject> getQuestionsById(Integer id);

    Subject getSubjectsById(Integer id);
}
