package com.example.Hotstar.model;

import com.example.Hotstar.enums.SubscriptionType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subscription {
    //Hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @CreationTimestamp
    Date startSubscriptionDate;
    @Column(nullable = false)
    double totalAmountPaid;

    //FROM DTO's
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    SubscriptionType subscriptionType;

    @Column(nullable = false)
    int noOfScreensSubscribed;

    //Mappings
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

}
