package com.example.Hotstar.controller;
import com.example.Hotstar.dto.SubscriptionRequest;
import com.example.Hotstar.dto.UserRequest;
import com.example.Hotstar.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    //Using the Bean object
    @Autowired
    SubscriptionService subscriptionService;

    @PostMapping("/buy")
    public ResponseEntity<?> buySubscription(@RequestBody SubscriptionRequest subscriptionRequest){
        try{
            return new ResponseEntity<>(subscriptionService.buySubscription(subscriptionRequest),HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/upgradeSubscription/{userId}")
    public ResponseEntity<?> upgradeSubscription(@PathVariable("userId") int userId){
        try{
            return new ResponseEntity<>(subscriptionService.upgradeSubscription(userId),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/calculateTotalRevenue")
    public double getTotalRevenue(){
        //calculate total revenue of hotstar from all the users combined
        return subscriptionService.getTotalRevenue();

    }






}
