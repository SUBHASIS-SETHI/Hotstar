package com.example.Hotstar.service;

import com.example.Hotstar.dto.UserRequest;
import com.example.Hotstar.enums.SubscriptionType;
import com.example.Hotstar.model.Matches;
import com.example.Hotstar.model.Subscription;
import com.example.Hotstar.model.User;
import com.example.Hotstar.model.Webseries;
import com.example.Hotstar.repo.MatchesRepo;
import com.example.Hotstar.repo.UserRepo;
import com.example.Hotstar.repo.WebseriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    //Using the Bean object
    @Autowired
    UserRepo userRepo;

    @Autowired
    WebseriesRepo webseriesRepo;

    @Autowired
    MatchesRepo matchesRepo;

    //Logic
    //1.Adding user into db
    public int addUser(UserRequest userRequest) {
        Optional<User> optionalUser=userRepo.findByEmail(userRequest.getEmail());

        //Duplicate entry not allowed
        if(optionalUser.isPresent())throw new RuntimeException("User already exists with this email");

        User user=new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        user.setEmail(userRequest.getEmail());
        user.setMobileNo(userRequest.getMobileNo());
        User savedUser=userRepo.save(user);
        return savedUser.getId();

    }

    public List<Webseries> getAvailableWebseriesViewable(int id) {
        Optional<User> optionalUser=userRepo.findById(id);
        if(optionalUser.isEmpty())throw new RuntimeException("User doesn't exist with this id");

        User user=optionalUser.get();
        //finding the matching age related and subscription type list of webseries for user
        int userAge=user.getAge();
        Subscription userSubscription=user.getSubscription();
        SubscriptionType userSubscriptionType;

          //choosing the user subscription type
        userSubscriptionType = userSubscription == null ? SubscriptionType.FREE : userSubscription.getSubscriptionType();

        return webseriesRepo.findBySubscriptionTypeAndAgeLimit(userSubscriptionType.name(), userAge);
    }

    public List<Matches> getAllMatches(int id){
        Optional<User> optionalUser=userRepo.findById(id);
        if(optionalUser.isEmpty())throw new RuntimeException("User doesn't exist with this id");
        User user=optionalUser.get();

        //checking the user subscription type
        Subscription userSubscription=user.getSubscription();
        if(userSubscription==null || userSubscription.getSubscriptionType()!=SubscriptionType.ELITE)throw new RuntimeException("User doesn't have Elite subscription to watch matches");

        //if Subscription is ELITE return all the matches list
        return matchesRepo.findAll();


    }

}
