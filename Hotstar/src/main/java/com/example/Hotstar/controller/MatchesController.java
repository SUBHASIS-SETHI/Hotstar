package com.example.Hotstar.controller;

import com.example.Hotstar.dto.MatchesRequest;
import com.example.Hotstar.dto.UserRequest;
import com.example.Hotstar.service.MatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matches")
public class MatchesController {
    @Autowired
    MatchesService matchesService;

    @PostMapping("/add")
    public ResponseEntity<?> addMatch(@RequestBody MatchesRequest matchesRequest){
        try{
            return new ResponseEntity<>(matchesService.addMatch(matchesRequest), HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMatchById(@RequestParam("matchId") int id){
        try{
            return new ResponseEntity<>(matchesService.deleteMatchById(id), HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteAllMatches")
    public ResponseEntity<?> deleteAllMatches(){
        try{
            return new ResponseEntity<>(matchesService.deleteAllMatches(), HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
