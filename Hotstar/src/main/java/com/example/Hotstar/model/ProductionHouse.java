package com.example.Hotstar.model;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductionHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    //FROM DTO's
    @Column(unique = true,nullable = false)
    String name;

    //from service layer
    @Column(nullable = false)
    double ratings;

    //Mappings
    @OneToMany(mappedBy = "productionHouse",cascade = CascadeType.ALL)
    List<Webseries> webseriesList=new ArrayList<>();

}
