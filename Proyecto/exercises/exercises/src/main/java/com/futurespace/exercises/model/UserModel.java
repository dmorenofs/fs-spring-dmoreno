package com.futurespace.exercises.model;

import java.time.LocalDate;


public class UserModel {
    private String name;
    private String firstSurname;
    private String secondSurname;
    private LocalDate birthDate;
    private String sex;
    private String userId;
    private String fullName;

    public UserModel(){}

    public UserModel(String name, String firstSurname, String secondSurname, LocalDate birthDate, String sex, String userId){
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
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName(){
        return name + " " + firstSurname + " " + secondSurname;
    }



    
    
}
