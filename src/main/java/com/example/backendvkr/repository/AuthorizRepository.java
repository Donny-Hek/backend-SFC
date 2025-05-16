package com.example.backendvkr.repository;

import com.example.backendvkr.model.Authoriz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorizRepository extends JpaRepository<Authoriz,Integer> {
    boolean existsByEmail(String email);

    Optional<Authoriz> findByEmail(String email);
}
