package com.futurespace.exercises.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//Class that will validate only the fields from the Put mapping method, it isn't required the same 
//but for the post method because the instructions require the post mapping to be using @RequestParam
//and in that case, the validation is done right next to the @RequestParam anotation
public class UpdateUserDTO {
    @Size(min=1, message = "Name cannot be empty if provided")
    private String name;
    @Size(min=1, message = "First surname cannot be empty if provided")
    private String firstSurname;
    @Size(min=1, message = "Second surname cannot be empty if provided")
    private String secondSurname;
    @Past
    private LocalDate birthDate;
    @Pattern(regexp = "^(M|F)$")
    private String sex;
    private String userId;
    private String fullName;

    public UpdateUserDTO(){}

    public UpdateUserDTO(String name, String firstSurname, String secondSurname, LocalDate birthDate, String sex, String userId){
        this.name = name;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.fullName = getFullName();
        this.birthDate = birthDate;
        this.sex = sex;
        this.userId = userId;
        

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFirstSurname() {
        return firstSurname;
    }
    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }
    public String getSecondSurname() {
        return secondSurname;
    }
    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getFullName(){
        return name + " " + firstSurname + " " + secondSurname;
    }



    
    
}
