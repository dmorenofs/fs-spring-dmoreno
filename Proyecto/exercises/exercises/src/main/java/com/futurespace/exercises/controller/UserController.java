package com.futurespace.exercises.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.futurespace.exercises.model.UpdateUserDTO;
import com.futurespace.exercises.model.UserModel;
import com.futurespace.exercises.seeder.Seeder;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/users")
@Validated
public class UserController {

  //Filling the user list for Exercise 1 and 3, with a seed function to keep the controller clean
  private List<UserModel> usersList = new ArrayList<>(Seeder.seedUsers());

    //Added a get all users method so we can check in Ejercicio 4 that the user is no longer in the list
    @GetMapping()
    public List<UserModel> getUsers() {
      return usersList;
    }

    /* 
     * Exercise 1
     * 
     */
    @GetMapping(path="/{userId}")
    public ResponseEntity<UserModel> getUser(@PathVariable String userId) {

      for (UserModel user: usersList){
        if (userId.equals(user.getUserId())){
          return new ResponseEntity<UserModel>(user, HttpStatus.OK);
        } 
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /* 
     * Exercise 2
     * 
     */
    //Following the doc requirements, we need to collect the data from the URL
    //First, we show the full list of users by calling the @GetMapping("/") controller with "http://localhost:8080/users"
    //There we can check that the user we are about to create is not already there
    //Then, we call the @PostMapping("/") controller, but adding the parameters in the url
    //Finally, we call again the get users method and we can se that the new user is now in the list 

    @PostMapping
    public ResponseEntity<UserModel>createUser(
      @RequestParam @NotBlank String name,
      @RequestParam @NotBlank String firstSurname,
      @RequestParam @NotBlank String lastSurname,
      @RequestParam @Past LocalDate birthDate,
      @RequestParam @Pattern(regexp = "^(M|F)$") String sex) {

      //It's also needed to create a new user object with the received info
      UserModel user = new UserModel(name, firstSurname, lastSurname, birthDate, sex, "1");
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
    
    /* 
     * Exercise 3
     *   
     */
    //First, we show the data of user with ID 1 by calling the @GetMapping("/{userId}") controller with "http://localhost:8080/users/1"
    //Then, we call the @PutMapping("/{userId}") controller with the same url, but now, we change the values via @RequestBody
    //Finally, we call again the get user method and we can se that the data has changed 

    @PutMapping(path = "/{userId}")
    public ResponseEntity<UserModel> updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDTO updatedUser) {
      UserModel storedUser = null;
      for (UserModel user: usersList){
        if (userId.equals(user.getUserId())){
          storedUser = user;
          break;
        } 
      }
      if (storedUser != null) {
        if (updatedUser.getName() != null) storedUser.setName(updatedUser.getName());
        if (updatedUser.getFirstSurname() != null) storedUser.setFirstSurname(updatedUser.getFirstSurname());
        if (updatedUser.getSecondSurname() != null) storedUser.setSecondSurname(updatedUser.getSecondSurname());
        if (updatedUser.getBirthDate() != null) storedUser.setBirthDate(updatedUser.getBirthDate());
        if (updatedUser.getSex() != null) storedUser.setSex(updatedUser.getSex());



        return new ResponseEntity<>(storedUser, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
        
    }

    /* 
     * Exercise 4
     *   
     */
    //First, we show the data of user with ID 1 by calling the @GetMapping("/{userId}") controller with "http://localhost:8080/users/1"
    //Then, we call the @DeleteMapping("/{userId}") controller with the same url
    //Finally, we call the get all users method and we can se that the user is no longer in the list
    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<UserModel> deleteUser(@PathVariable String userId) {
      UserModel storedUser = null;
      for (UserModel user: usersList){
        if (userId.equals(user.getUserId())){
          storedUser = user;
          break;
        } 
      }
      if (storedUser != null) {
        usersList.remove(storedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
        
    }


  }
