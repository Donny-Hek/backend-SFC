package com.example.backendvkr.repository;

import com.example.backendvkr.model.Examination;
import com.example.backendvkr.model.Subscription;
import com.example.backendvkr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserById(Integer id);

    User getUserById(Integer id);

    List<Examination> getExaminationsById(Integer id);
}
