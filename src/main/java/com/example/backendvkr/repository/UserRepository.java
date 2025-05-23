package com.example.backendvkr.repository;

import com.example.backendvkr.model.Authoriz;
import com.example.backendvkr.model.Examination;
import com.example.backendvkr.model.Subscription;
import com.example.backendvkr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserById(Integer id);

    User getUserById(Integer id);
    @Query("SELECT u FROM User u JOIN u.authoriz a WHERE a.email = :authorizEmail")
    Optional<User> findByAuthoriz_Email(@Param("authorizEmail")String authorizEmail);
}
