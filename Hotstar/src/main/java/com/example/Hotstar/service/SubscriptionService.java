package com.example.Hotstar.service;

import com.example.Hotstar.dto.SubscriptionRequest;
import com.example.Hotstar.enums.SubscriptionType;
import com.example.Hotstar.model.Subscription;
import com.example.Hotstar.model.User;
import com.example.Hotstar.repo.SubscriptionRepo;
import com.example.Hotstar.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    SubscriptionRepo subscriptionRepo;

    public String buySubscription(SubscriptionRequest subscriptionRequest) {
        int tempUserId=subscriptionRequest.getUserId();
        Optional<User> optionalUser=userRepo.findById(tempUserId);
        if(optionalUser.isEmpty())throw new RuntimeException("Invalid userId provided");

        User user=optionalUser.get();
             //--** Subscription buy rule: --**    \\
           //The subscription costing goes something like this. There is only monthly subscription possible:
          // For Basic Plan: 500 + 200 * noOfScreensSubscribed
         // For PRO Plan: 800 + 250 * noOfScreensSubscribed
        // For ELITE Plan: 1000 + 350 * noOfScreensSubscribed
        SubscriptionType requestedSubscriptionType=subscriptionRequest.getSubscriptionType();
        int noOfScreensChoosed= subscriptionRequest.getNoOfScreensRequired();
        double totalAmount=0;
        if(requestedSubscriptionType==SubscriptionType.BASIC){
            totalAmount+=500+200*noOfScreensChoosed;
        }else if(requestedSubscriptionType==SubscriptionType.PRO){
            totalAmount+=800+250*noOfScreensChoosed;
        }else{
            totalAmount+=1000+350*noOfScreensChoosed;//else its ELITE
        }
        Subscription subscription=new Subscription();
        subscription.setTotalAmountPaid(totalAmount);
        subscription.setSubscriptionType(requestedSubscriptionType);
        subscription.setNoOfScreensSubscribed(noOfScreensChoosed);
        subscription.setUser(user);

        user.setSubscription(subscription);
        userRepo.save(user);//saving subscription and user together




        return "Total amount for subscription :"+totalAmount;

    }

    public String upgradeSubscription(int userId) {
        Optional<User> optionalUser=userRepo.findById(userId);
        if(optionalUser.isEmpty())throw new RuntimeException("Invalid userId provided");

        User user=optionalUser.get();

        //we need to upgrade 1 tier
        //suppose if we are BASIC change to PRO
        //if PRO change to ELITE
        //If already ELITE throw exception

        Subscription subscription=user.getSubscription();

        if(subscription==null){
           throw new RuntimeException("No subscription found, first buy subscription then upgrade");
        }
        SubscriptionType alreadySubscriptionType=subscription.getSubscriptionType();
        int noOfScreenSubscribed=subscription.getNoOfScreensSubscribed();
        double alreadyPaidMoney=subscription.getTotalAmountPaid();
        double extraNeedToPay=0;

        if(alreadySubscriptionType==SubscriptionType.ELITE){
            throw new RuntimeException("Already upgraded to top subscription no further upgrade available");
        }else if(alreadySubscriptionType==SubscriptionType.PRO){
            //Convert PRO -> ELITE
            extraNeedToPay+=(1000+350*noOfScreenSubscribed)-alreadyPaidMoney;
            subscription.setSubscriptionType(SubscriptionType.ELITE);

        }else if(alreadySubscriptionType==SubscriptionType.BASIC) {
            //convert BASIC-> PRO
            extraNeedToPay += (800 + 250 * noOfScreenSubscribed) - alreadyPaidMoney;
            subscription.setSubscriptionType(SubscriptionType.PRO);
        }
        subscription.setTotalAmountPaid(extraNeedToPay+alreadyPaidMoney);

         subscriptionRepo.save(subscription);
        return "Total amount for upgrade :"+extraNeedToPay;

    }

    public double getTotalRevenue() {
        double totalRevenue=0;

        //Find all Subscribers and total the amount paid by them
        List<Subscription> subscriptionList=subscriptionRepo.findAll();
        for(Subscription eachSubscription:subscriptionList){
            totalRevenue+=eachSubscription.getTotalAmountPaid();
        }
        return totalRevenue;
    }
}
