package com.example.backendvkr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshToken extends JpaRepository<RefreshToken, Integer> {
}
