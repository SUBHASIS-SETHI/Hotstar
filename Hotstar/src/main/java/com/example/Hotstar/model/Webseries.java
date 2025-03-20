package com.example.Hotstar.model;

import com.example.Hotstar.enums.SubscriptionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Webseries{
    //Hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    //FROM DTO's
    @Column(unique = true,nullable = false)
    String seriesName;

    @Column(nullable = false)
    int ageLimit;
    @Column(nullable = false)
    double ratings;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    SubscriptionType subscriptionType;



    //Mapping
    @ManyToOne
    @JoinColumn(name = "production_id")
    @JsonIgnore
    ProductionHouse productionHouse;

}
