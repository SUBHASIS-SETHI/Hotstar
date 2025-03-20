package com.example.Hotstar.controller;

import com.example.Hotstar.dto.ProductionHouseRequest;
import com.example.Hotstar.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/production")
public class ProductionController {
    //Using the Bean object
    @Autowired
    ProductionService productionService;


    //1.Add a ProductionHouse
    @PostMapping("/add")
    public ResponseEntity<?> addProductionHouse(@RequestBody ProductionHouseRequest productionHouseRequest){
        try{
            return new ResponseEntity<>(productionService.addProductionHouse(productionHouseRequest), HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }

    }

}
