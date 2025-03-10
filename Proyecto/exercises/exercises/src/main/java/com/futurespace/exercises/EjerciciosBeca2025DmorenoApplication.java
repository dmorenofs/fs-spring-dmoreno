package com.futurespace.exercises;


import com.futurespace.exercises.model.Autor;
import com.futurespace.exercises.model.Editorial;
import com.futurespace.exercises.model.Libro;
import com.futurespace.exercises.model.Tematica;
import com.futurespace.exercises.repository.IAutorRepository;
import com.futurespace.exercises.repository.IEditorialRepository;
import com.futurespace.exercises.repository.ILibroRepository;
import com.futurespace.exercises.repository.ITematicaRepository;
import com.futurespace.exercises.seeder.DbSeeder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class EjerciciosBeca2025DmorenoApplication {
    public static void main(String[] args) {
      SpringApplication.run(EjerciciosBeca2025DmorenoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(

            ITematicaRepository tematicaRepository,
            IEditorialRepository editorialRepository,
            IAutorRepository autorRepository,
            ILibroRepository libroRepository){

        return args -> {

            //Inicializamos la base de datos en otro archivo con los mismos datos que en el curso SQL
            DbSeeder seeder = new DbSeeder(tematicaRepository,editorialRepository,autorRepository,libroRepository);
            seeder.initDB();

            //En los ejercicios 4 y 5 se pide que el resultado se muestre por pantalla, por eso están aquÍ, el resto con postman
            //Ejercicio 4
            List<Libro> books = libroRepository.findBooksPublishedAfter2001QUERY();
            System.out.println("Libros publicados después de 2001:");
            printBooks(books);

            //Ejercicio 5
            List<Libro> books2 = libroRepository.findByAnioPublicacionGreaterThan(2001);
            System.out.println("Libros publicados después de 2001:");
            printBooks(books2);

        };
    }

    private static void printBooks(List<Libro> books) {
        System.out.println("===========================================");
        if (books.isEmpty()) {
            System.out.println("No hay libros que cumplan la condición");
        } else {
            books.forEach(libro -> System.out.printf("%s - %s\n", libro.getTitulo(), libro.getAnioPublicacion()));
        }
    }

    /*
     * Health check to verify the app is working.
     * This mapping has 0 dependencies other than the web server, so it should always work
     */
    @GetMapping("/health")
    public String hello() {
      return "health check ok";
    }
}
