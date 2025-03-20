package com.example.Hotstar.model;

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
public class User {
    //Hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    //FROM DTO's
    @Column(nullable = false)
    String name;
    @Column(unique = true,nullable = false)
    String email;

    @Column(nullable = false)
    int age;
    @Column(nullable = false)
    String mobileNo;

    //Mappings
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    Subscription subscription;



}
