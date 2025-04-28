package com.example.backendvkr.repository;

import com.example.backendvkr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
