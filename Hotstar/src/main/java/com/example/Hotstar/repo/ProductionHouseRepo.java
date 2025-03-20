package com.example.Hotstar.repo;

import com.example.Hotstar.model.ProductionHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionHouseRepo extends JpaRepository<ProductionHouse,Integer> {
    ProductionHouse findByName(String name);
}
