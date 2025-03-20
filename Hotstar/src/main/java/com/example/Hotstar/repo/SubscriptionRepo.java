package com.example.Hotstar.repo;

import com.example.Hotstar.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepo extends JpaRepository<Subscription,Integer> {
}
