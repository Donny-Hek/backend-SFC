package com.example.backendvkr.repository;

import com.example.backendvkr.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription,Integer> {
    Subscription getSubscriptionBySubsType(String subsType);
}
