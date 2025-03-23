package com.example.Hotstar.repo;

import com.example.Hotstar.enums.Sports;
import com.example.Hotstar.model.Matches;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchesRepo extends JpaRepository<Matches,Integer> {
    boolean existsBySportsAndDateAndTeam1AndTeam2AndVenue(Sports sports, String date, String team1, String team2, String venue);
}
