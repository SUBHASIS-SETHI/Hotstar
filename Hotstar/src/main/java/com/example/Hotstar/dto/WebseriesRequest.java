package com.example.Hotstar.dto;

import com.example.Hotstar.enums.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WebseriesRequest {
    String seriesName;
    int ageLimit;
    double ratings;
    SubscriptionType subscriptionType;
    int productionHouseId;
}
