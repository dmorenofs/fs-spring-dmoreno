package com.futurespace.exercises;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EjerciciosBeca2025DmorenoApplication {
    public static void main(String[] args) {
      SpringApplication.run(EjerciciosBeca2025DmorenoApplication.class, args);
    }
    @GetMapping("/Hey")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
      return String.format("Hello %s!", name);
    }
}