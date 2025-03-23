package com.example.Hotstar.repo;

import com.example.Hotstar.enums.SubscriptionType;
import com.example.Hotstar.model.Webseries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WebseriesRepo extends JpaRepository<Webseries,Integer> {
    boolean existsBySeriesName(String webSeriesName);

//    @Query("SELECT w FROM Webseries w WHERE w.subscriptionType = :subscriptionType AND w.ageLimit <= :age")
//    List<Webseries> findBySubscriptionTypeAndAgeLimit(@Param("subscriptionType") SubscriptionType subscriptionType, @Param("age") int age);

    @Query("""
        SELECT w FROM Webseries w
        WHERE w.ageLimit <= :age AND 
              (
                  w.subscriptionType = 'FREE' OR 
                  (w.subscriptionType = 'PRO' AND :subscriptionType IN ('PRO', 'ELITE'))
              )
    """)
    List<Webseries> findBySubscriptionTypeAndAgeLimit(
            @Param("subscriptionType") String subscriptionType,
            @Param("age") int age
    );
}
