package com.example.backendvkr.repository;

import com.example.backendvkr.model.Authoriz;
import com.example.backendvkr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserById(Integer id);
//    Optional<User> findByEmail(Authoriz email);
//    boolean existsByEmail(Authoriz email);

//    Optional<User> findByUsername(String username);
}
