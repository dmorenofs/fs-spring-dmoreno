package com.futurespace.exercises;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EjerciciosBeca2025DmorenoApplication {
    public static void main(String[] args) {
      SpringApplication.run(EjerciciosBeca2025DmorenoApplication.class, args);
    }
    /* 
     * Health check to verify the app is working.
     * This mapping has 0 dependencies other than the web server, so it should always work
     */
    @GetMapping("/health")
    public String hello() {
      return String.format("health check ok");
    }
}
