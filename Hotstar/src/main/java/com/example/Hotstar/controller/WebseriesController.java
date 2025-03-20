package com.example.Hotstar.controller;

import com.example.Hotstar.dto.WebseriesRequest;
import com.example.Hotstar.service.WebseriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webseries")
public class WebseriesController {

    @Autowired
    WebseriesService webseriesService;

    @PostMapping("/add")
    public ResponseEntity<?> addWebseries(@RequestBody WebseriesRequest webseriesRequest){
        try{
            return new ResponseEntity<>(webseriesService.addWebseries(webseriesRequest),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
