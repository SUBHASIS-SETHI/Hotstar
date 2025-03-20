package com.example.Hotstar.dto;

import com.example.Hotstar.enums.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionRequest {
    int userId;
    int noOfScreensRequired;
    SubscriptionType subscriptionType;
}
