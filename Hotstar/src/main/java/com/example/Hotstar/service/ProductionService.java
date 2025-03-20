package com.example.Hotstar.service;

import com.example.Hotstar.dto.ProductionHouseRequest;
import com.example.Hotstar.model.ProductionHouse;
import com.example.Hotstar.model.Webseries;
import com.example.Hotstar.repo.ProductionHouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionService {
    @Autowired
    ProductionHouseRepo productionHouseRepo;

    public int addProductionHouse(ProductionHouseRequest productionHouseRequest) {
        String productHouseName=productionHouseRequest.getName().trim().toUpperCase();
        ProductionHouse productionHouse=productionHouseRepo.findByName(productHouseName);

        if(productionHouse!=null)throw new RuntimeException("Same ProductionHouse name already exists");

        productionHouse=new ProductionHouse();
        productionHouse.setName(productHouseName);

        //calculating average rating of Production House
        List<Webseries> webseriesList=productionHouse.getWebseriesList();
        double avgRatingsOfAllWebseries=0;
        for(Webseries eachWebseries:webseriesList){
           avgRatingsOfAllWebseries+= eachWebseries.getRatings();
        }
        if(webseriesList.size()>0){
            avgRatingsOfAllWebseries/=webseriesList.size();
        }

        productionHouse.setRatings(avgRatingsOfAllWebseries);

        ProductionHouse savedProductionHouse=productionHouseRepo.save(productionHouse);

        return savedProductionHouse.getId();

    }
}
