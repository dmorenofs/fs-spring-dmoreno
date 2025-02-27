package com.futurespace.exercises.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.futurespace.exercises.service.UserService;

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

    //@Autowired is not needed since there's only 1 class constructor, according to Spring docs
    private final UserService userService;
    public UserController(UserService userService){
      this.userService = userService;
    }


    //Added a get all users method so we can check in Ejercicio 4 that the user is no longer in the list
    @GetMapping()
    public List<UserModel> getUsers() {
      return userService.getAllUsers();
    }

    /* 
     * Exercise 1
     * 
     */
    @GetMapping(path="/{userId}")
    public ResponseEntity<UserModel> getUser(@PathVariable String userId) {
      /* 
       * If the Optional returned by userService.getUserById(userId) contains a user, 
       * then with .map() we transform that user into a ResponseEntity with the user and status OK
       * If the Optional is empty, .orElse() returns a ResponseEntity with status NOT_FOUND
       */
      return userService.getUserById(userId)
              .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
              .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
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

      //create a new user object with the received info
      UserModel user = userService.createUser(name, firstSurname, lastSurname, birthDate, sex);
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
      
      return userService.updateUser(userId, updatedUser)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /* 
     * Exercise 4
     *   
     */
    //First, we show the data of user with ID 1 by calling the @GetMapping("/{userId}") controller with "http://localhost:8080/users/1"
    //Then, we call the @DeleteMapping("/{userId}") controller with the same url
    //Finally, we call the get all users method and we can se that the user is no longer in the list
    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {

      boolean deleted = userService.deleteUser(userId);
      if (deleted) {
        return new ResponseEntity<>(HttpStatus.OK);
      }else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
        
    }


  }
