package com.example.Hotstar.dto;

import com.example.Hotstar.enums.Sports;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MatchesRequest {
    Sports sports;
    String date;
    String team1;
    String team2;
    String venue;
}
