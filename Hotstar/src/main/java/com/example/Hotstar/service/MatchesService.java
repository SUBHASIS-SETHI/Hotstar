package com.example.Hotstar.service;

import com.example.Hotstar.dto.MatchesRequest;
import com.example.Hotstar.enums.Sports;
import com.example.Hotstar.model.Matches;
import com.example.Hotstar.repo.MatchesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatchesService {

    @Autowired
    MatchesRepo matchesRepo;

    public int addMatch(MatchesRequest matchesRequest) {
        Sports sport=matchesRequest.getSports();
        String date=matchesRequest.getDate();
        String team1=matchesRequest.getTeam1();
        String team2=matchesRequest.getTeam2();
        String venue=matchesRequest.getVenue();

        if(matchesRepo.existsBySportsAndDateAndTeam1AndTeam2AndVenue(sport, date, team1, team2, venue)){
            throw new RuntimeException("Same match already exists");
        }
        if(team1.equals(team2))throw new RuntimeException("Team1 and Team2 can't be same");

        Matches matches=new Matches();
        matches.setDate(date);
        matches.setTeam1(team1);
        matches.setTeam2(team2);
        matches.setSports(sport);
        matches.setVenue(matchesRequest.getVenue());

        Matches savedMatches=matchesRepo.save(matches);
        return savedMatches.getId();

    }

    public String deleteMatchById(int id) {
        //check if valid match id or not
        Optional<Matches> optionalMatch=matchesRepo.findById(id);
        if(optionalMatch.isEmpty())throw new RuntimeException("Match doesn't exists with matchId: "+id);


         matchesRepo.deleteById(id);
        return "Match deleted successfully with matchId: "+id;


    }

    public String deleteAllMatches() {
        matchesRepo.deleteAll();
        return "All matches deleted Successfully";
    }
}
