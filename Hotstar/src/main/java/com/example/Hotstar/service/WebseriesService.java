package com.example.Hotstar.service;

import com.example.Hotstar.dto.WebseriesRequest;
import com.example.Hotstar.model.ProductionHouse;
import com.example.Hotstar.model.Webseries;
import com.example.Hotstar.repo.ProductionHouseRepo;
import com.example.Hotstar.repo.WebseriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebseriesService {
    @Autowired
    ProductionHouseRepo productionHouseRepo;
    @Autowired
    WebseriesRepo webseriesRepo;


    public int addWebseries(WebseriesRequest webseriesRequest) {
        //check if duplicate webseries entered or not
        String webSeriesName= webseriesRequest.getSeriesName().trim().toUpperCase();
        if(webseriesRepo.existsBySeriesName(webSeriesName))throw new RuntimeException("Error: Same Webseries name already exists");

        //check if provided ProductionHouse id is valid or not
        int tempProductionHouseId=webseriesRequest.getProductionHouseId();
        Optional<ProductionHouse> optionalProductionHouse=productionHouseRepo.findById(tempProductionHouseId);
        if(optionalProductionHouse.isEmpty())throw new RuntimeException("Invalid productionHouseId provided");

        //if Production House present add webseries
        ProductionHouse currProductionHouse=optionalProductionHouse.get();

        List<Webseries> webseriesList=currProductionHouse.getWebseriesList();
        int size=webseriesList.size();
        double totalSumOfProductionHouseRating=currProductionHouse.getRatings()*size;
        double updatedRating=(totalSumOfProductionHouseRating + webseriesRequest.getRatings()) / (size+1);
        currProductionHouse.setRatings(updatedRating);


        Webseries webseries=new Webseries();
        webseries.setSeriesName(webSeriesName);
        webseries.setAgeLimit(webseriesRequest.getAgeLimit());
        webseries.setRatings(webseriesRequest.getRatings());
        webseries.setSubscriptionType(webseriesRequest.getSubscriptionType());
        webseries.setProductionHouse(currProductionHouse);


        webseriesList.add(webseries);

        //save in db both web and production repo
        Webseries savedWebseries=webseriesRepo.save(webseries);
        productionHouseRepo.save(currProductionHouse);

        return savedWebseries.getId();


    }
}
