package com.futurespace.exercises;


import com.futurespace.exercises.model.Autor;
import com.futurespace.exercises.model.Editorial;
import com.futurespace.exercises.model.Libro;
import com.futurespace.exercises.model.Tematica;
import com.futurespace.exercises.repository.IAutorRepository;
import com.futurespace.exercises.repository.IEditorialRepository;
import com.futurespace.exercises.repository.ILibroRepository;
import com.futurespace.exercises.repository.ITematicaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class EjerciciosBeca2025DmorenoApplication {
    public static void main(String[] args) {
      SpringApplication.run(EjerciciosBeca2025DmorenoApplication.class, args);
    }

    //Inserciones para manejar la base de datos con SpringJPA
    @Bean
    public CommandLineRunner initDatabase(
            ITematicaRepository tematicaRepository,
            IEditorialRepository editorialRepository,
            IAutorRepository autorRepository,
            ILibroRepository libroRepository){

        return args -> {
            System.out.println("Cargando editoriales");
            List<Editorial> editoriales = Arrays.asList(
                    new Editorial("PLANETA", "PLANETA S.A."),
                    new Editorial("O'REILLY", "O'REILLY S.A."),
                    new Editorial("RBA", "RBA S.A."));
            editorialRepository.saveAll(editoriales);

            System.out.println("Cargando temáticas");
            List<Tematica> tematicas = Arrays.asList(
                    new Tematica("HISTORIA"),
                    new Tematica("INFORMATICA"),
                    new Tematica("NOVELA"));
            tematicaRepository.saveAll(tematicas);

            System.out.println("Cargando autores");
            List<Autor> autores = Arrays.asList(
                    new Autor("FRANCISCO","DIAZ DIAZ"),
                    new Autor("ALBERTO","MIGUELEZ LOPEZ"),
                    new Autor("RAUL","OCHANDIANO RIVERA"),
                    new Autor("FERNANDO","MARTOS ETXEBERRIA"),
                    new Autor("JOANNE","ROWLING"),
                    new Autor("MIGUEL","LIMON IGNACIO"),
                    new Autor("DAVID","MOZAS JEREZ"));
            autorRepository.saveAll(autores);

            /*Para evitar duplicidad en la base de datos como la que ocurriria, por ejemplo, si en el campo Tematica
            * de la tabla libro hacemos: new Tematica("HISTORIA") para reflejar que ese libro es de Historia,
            * debemos hacer la creación de libros mediante referencias, es decir, debemos obtener de la base de datos
            * la temática historia, y entonces aplicar eso mismo (con ese ID) a un libro. En el código se entiende mejor
             */

            //Obtenemos referencias a entidades existentes:
            //Temáticas
            Tematica historia = tematicaRepository.findByCategoria("HISTORIA").orElseThrow();
            Tematica informatica = tematicaRepository.findByCategoria("INFORMATICA").orElseThrow();
            Tematica novela = tematicaRepository.findByCategoria("NOVELA").orElseThrow();
            //Editoriales
            Editorial planeta = editorialRepository.findByNombre("PLANETA").orElseThrow();
            Editorial oreilly = editorialRepository.findByNombre("O'REILLY").orElseThrow();
            Editorial rba = editorialRepository.findByNombre("RBA").orElseThrow();
            //AUTORES
            Autor franciscoDiaz = autorRepository.findByNombreAndApellidos("FRANCISCO", "DIAZ DIAZ").orElseThrow();
            Autor albertoMiguelez = autorRepository.findByNombreAndApellidos("ALBERTO", "MIGUELEZ LOPEZ").orElseThrow();
            Autor raulOchandiano = autorRepository.findByNombreAndApellidos("RAUL", "OCHANDIANO RIVERA").orElseThrow();
            Autor fernandoMartos = autorRepository.findByNombreAndApellidos("FERNANDO", "MARTOS ETXEBERRIA").orElseThrow();
            Autor joanneRowling = autorRepository.findByNombreAndApellidos("JOANNE", "ROWLING").orElseThrow();
            Autor miguelLimon = autorRepository.findByNombreAndApellidos("MIGUEL", "LIMON IGNACIO").orElseThrow();
            Autor davidMozas = autorRepository.findByNombreAndApellidos("DAVID", "MOZAS JEREZ").orElseThrow();

            //Creamos los libros usando las referencias para evitar duplicidad:
            System.out.println("Cargando autores");
            List<Libro> libros = Arrays.asList(
                    new Libro("123456783","HISTORIA DE ALEMANIA", 2001, franciscoDiaz, planeta, historia),
                    new Libro("123456784","HISTORIA DE ESPAÑA", 2002, franciscoDiaz, planeta, historia),
                    new Libro("123456785","HISTORIA DE FRANCIA", 2002, franciscoDiaz, planeta, historia),
                    new Libro("123456786","LA SOCIEDAD MEDIEVAL", 2001, albertoMiguelez, planeta, historia),
                    new Libro("123456787","ANGULAR DESDE 0", 2013, raulOchandiano, oreilly, informatica),
                    new Libro("123456788","CREPÚSCULO VAMPIROS", 1999, fernandoMartos, rba, novela),
                    new Libro("123456789","CREPÚSCULO ZOMBIES", 1998, fernandoMartos, rba, novela),
                    new Libro("123456790","CREPÚSCULO MONSTERS", 1997, fernandoMartos, rba, novela),
                    new Libro("123456791","HARRY POTTER I", 2000, joanneRowling, rba, novela),
                    new Libro("123456792","HARRY POTTER II", 2001, joanneRowling, rba, novela),
                    new Libro("123456793","HARRY POTTER III", 2001, joanneRowling, rba, novela),
                    new Libro("123456794","HARRY POTTER IV",2002, joanneRowling, rba, novela),
                    new Libro("123456795","LA SOCIEDAD INDUSTRIAL", 1972, albertoMiguelez, planeta, historia),
                    new Libro("123456796","LOS TEMPLARIOS", 1998, miguelLimon, planeta, historia),
                    new Libro("123456797","LOS ALBIGENSES",1986, miguelLimon, planeta, historia),
                    new Libro("123456798","LOS GODOS", 2001, miguelLimon, planeta, historia),
                    new Libro("123456799","SPRING", 2014, davidMozas, oreilly, informatica),
                    new Libro("123456800","JQUERY", 2015, davidMozas, oreilly, informatica),
                    new Libro("123456801","MYSQL", 2016, davidMozas, oreilly, informatica),
                    new Libro("87919878","ORACLE", 2003, davidMozas, oreilly, informatica));

            libroRepository.saveAll(libros);

            System.out.println("Base de datos inicializada correctamente");
            System.out.println("===========================================");


            //Ejercicio 4
            List<Libro> books = libroRepository.findBooksPublishedAfter2001QUERY();
            System.out.println("Libros publicados después de 2001:");
            printBooks(books);

            //Ejercicio 5
            List<Libro> books2 = libroRepository.findByAñoPublicacionGreaterThan(2001);
            System.out.println("Libros publicados después de 2001:");
            printBooks(books2);

            //Ejercicio 6.1
            List<Libro> books3 = libroRepository.findByAñoPublicacionIs(2001);
            System.out.println("Libros publicados en 2001:");
            printBooks(books3);

            //Ejercicio 6.2
            List<Libro> books4 = libroRepository.findByIsbnIs("87919878");
            System.out.println("Libro con isbn 87919878:");
            printBooks(books4);

            //Ejercicio 6.3
            List<Libro> books5 = libroRepository.findByEditorial_Nombre("RBA");
            System.out.println("Libros de RBA:");
            printBooks(books5);

            //Ejercicio 6.4
            List<Libro> books6 = libroRepository.findByAñoPublicacionIsAndEditorial_Nombre(1986, "PLANETA");
            System.out.println("Libros de Planeta publicados en 1986:");
            printBooks(books6);

        };
    }

    private static void printBooks(List<Libro> books) {
        System.out.println("===========================================");
        if (books.isEmpty()) {
            System.out.println("No hay libros que cumplan la condición");
        } else {
            books.forEach(libro -> System.out.printf("%s - %s\n", libro.getTitulo(), libro.getAñoPublicacion()));
        }
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
