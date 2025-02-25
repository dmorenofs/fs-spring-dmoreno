package com.futurespace.exercises.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.futurespace.exercises.model.UserModel;



@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping()
    public String getUsers() {
      return "getUsers was called";
    }

    //We are not checking if the userId is correct, that will be done later when we implement services and repositories
    @GetMapping("/{userId}")
    public UserModel getUser(@PathVariable String userId) {
      //Creating a user using the bean UserModel
      UserModel user = new UserModel(
        "Juan",
        "Salazar",
        "Martin",
        LocalDate.of(2001, 2, 2),
        "Hombre",
        userId
        
      );

        return user ;
    }
    
    
}