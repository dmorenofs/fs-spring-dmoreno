package com.futurespace.exercises.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.futurespace.exercises.model.UserModel;



@RestController
@RequestMapping("/users")
public class UserController {

  private List<UserModel> usersList = new ArrayList<>();

    @GetMapping()
    public String getUsers() {
      return "getUsers was called";
    }

    /* 
     * Exercise 1
     * 
     */
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

    /* 
     * Exercise 2
     * 
     */
    //Following the doc requirements, we need to collect the data from the URL
    @PostMapping
    public ResponseEntity<UserModel>createUser(
      @RequestParam String name,
      @RequestParam String firstSurname,
      @RequestParam String lastSurname,
      @RequestParam String birthDate,
      @RequestParam String sex) {

      //Since birthDate is a string, we need to parse it to a LocalDate type
      LocalDate parsedBirthDate = LocalDate.parse(birthDate);
      //It's also needed to create a new user object with the received info
      UserModel user = new UserModel(name, firstSurname, lastSurname, parsedBirthDate, sex, "1");
      //Store the user in a list
      usersList.add(user);
      //It is requested to display the user info in the app console in a structured form
      System.out.println("*****************");
      System.out.println("Data:");
      System.out.println("Name: " + user.getName());
      System.out.println("First surname: " + user.getFirstSurname());
      System.out.println("Second surname: " + user.getSecondSurname());
      System.out.println("Birth date: " + user.getBirthDate());
      System.out.println("Sex: " + user.getSex());

      return new ResponseEntity<UserModel>(user, HttpStatus.CREATED);
    }
    
    
}