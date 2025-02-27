package com.futurespace.exercises.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.futurespace.exercises.model.UpdateUserDTO;
import com.futurespace.exercises.model.UserModel;
import com.futurespace.exercises.seeder.Seeder;

@Service
public class UserServiceImpl implements UserService {
    
    //Filling the user list for Exercise 1 and 3, with a seed function to keep the controller clean
    private List<UserModel> usersList = new ArrayList<>(Seeder.seedUsers());

    @Override
    public List<UserModel> getAllUsers(){
        return usersList;
    }

    /* This is very interesting and I found it online, basically we use Optional type instead of UserModel
     * as the return param, because the user may not exist, and if we use UserModel, we would have to add verifications
     * but using the Optional, we don't care at this point if there's an user or not, and later, in the controller,
     * we can filter it using a .map .orElse
     */
    @Override
    public Optional<UserModel> getUserById(String userId){
        /* What this does is, it takes the list and divides it into chunks(users), to these chunks it passes a check, 
        and takes the first element that meets that check, as the id is unique, that element is also unique.
        If some element meets the check, it returns Optional<element>, if not, returns an empty optional */
        return usersList.stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst();
    }

    //In this case we don't need the Optional since we know that an user is always returned
    @Override
    public UserModel createUser(String name, String firstSurname, String secondSurname, LocalDate birthDate, String sex){
        UserModel user = new UserModel(name, firstSurname, secondSurname, birthDate, secondSurname, UUID.randomUUID().toString());
        usersList.add(user);
      
        //It is requested to display the user info in the app console in a structured form
        System.out.println("*****************");
        System.out.println("Data:");
        System.out.println("Name: " + user.getName());
        System.out.println("First surname: " + user.getFirstSurname());
        System.out.println("Second surname: " + user.getSecondSurname());
        System.out.println("Birth date: " + user.getBirthDate());
        System.out.println("Sex: " + user.getSex());

        return user;
    }

    @Override
    public Optional<UserModel> updateUser(String userId, UpdateUserDTO updatedUser){
        Optional<UserModel> userOpt = getUserById(userId);
        /* As we are using optionals, we cannot use the classic if user != null, since we 
        cannot do user.setName of an Optional, we must do the check and “uncapsulate” 
        the optional to be able to operate with it. And .ifPresent give us that option */
        userOpt.ifPresent(user ->{
            if (updatedUser.getName()!= null) user.setName(updatedUser.getName());
            if (updatedUser.getFirstSurname()!=null) user.setFirstSurname(updatedUser.getFirstSurname());
            if (updatedUser.getSecondSurname()!=null) user.setSecondSurname(updatedUser.getSecondSurname());
            if (updatedUser.getBirthDate()!=null) user.setBirthDate(updatedUser.getBirthDate());
            if (updatedUser.getSex()!=null) user.setSex(updatedUser.getSex());

        });

        return userOpt;
    }

    @Override
    public boolean deleteUser(String userId) {
        Optional<UserModel> userOpt = getUserById(userId);
        //Since we are using Optionals, we can easily check if the user exist using .isPresent()
        if (userOpt.isPresent()){
            usersList.remove(userOpt.get());
            return true;
        }
        return false;
    }

    
    
}
