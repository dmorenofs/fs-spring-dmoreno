package com.futurespace.exercises.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    @GetMapping("/users")
    public String getUser() {
      return "getUser was called";
    }
    
}