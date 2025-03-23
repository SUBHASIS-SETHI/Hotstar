package com.example.Hotstar.model;

import com.example.Hotstar.enums.Sports;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Matches {
    //Hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    //FROM DTO's
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Sports sports;

    @Column(nullable = false)
    String date;

    @Column(nullable = false)
    String team1;

    @Column(nullable = false)
    String team2;

    @Column(nullable = false)
    String venue;



}
