package com.example.backendvkr.repository;

import com.example.backendvkr.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Integer> {
    Subscription getSubscriptionBySubsType(String subsType);
}
