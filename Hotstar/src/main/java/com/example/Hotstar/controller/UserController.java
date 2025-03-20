package com.example.Hotstar.controller;

import com.example.Hotstar.dto.UserRequest;
import com.example.Hotstar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/user")
public class UserController {

    //Using the Bean object
    @Autowired
    UserService userService;


    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest){
        try{
            return new ResponseEntity<>(userService.addUser(userRequest),HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/getAvailableWebseries")
    public ResponseEntity<?> getAvailableWebseriesViewable(@RequestParam("userId") int id){
        try{
            return new ResponseEntity<>(userService.getAvailableWebseriesViewable(id),HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

}
