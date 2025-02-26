package com.futurespace.exercises.seeder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.futurespace.exercises.model.UserModel;


public class Seeder {

    public static List<UserModel> seedUsers(){
        List<UserModel> users = new ArrayList<>();

        users.add(new UserModel("Jhon", "Doe", "Smith", LocalDate.parse("1998-03-03"), "M","1"));
        users.add(new UserModel("Alice", "Johnson", "Williams", LocalDate.parse("1995-06-15"), "F", "2"));
        users.add(new UserModel("Michael", "Brown", "Jones", LocalDate.parse("1989-11-22"), "M", "3"));
        users.add(new UserModel("Emily", "Davis", "Miller", LocalDate.parse("2000-09-09"), "F", "4"));
        users.add(new UserModel("David", "Martinez", "Garcia", LocalDate.parse("1992-12-01"), "M", "5"));
        users.add(new UserModel("Sophia", "Hernandez", "Rodriguez", LocalDate.parse("1997-05-19"), "F", "6"));
        users.add(new UserModel("James", "Lopez", "Perez", LocalDate.parse("1988-07-30"), "M", "7"));
        users.add(new UserModel("Olivia", "Gonzalez", "Wilson", LocalDate.parse("1996-02-11"), "F", "8"));
        users.add(new UserModel("William", "Anderson", "Thomas", LocalDate.parse("1990-08-17"), "M", "9"));
        users.add(new UserModel("Isabella", "Taylor", "Moore", LocalDate.parse("1999-01-29"), "F", "10"));

        return users;
    }
}
