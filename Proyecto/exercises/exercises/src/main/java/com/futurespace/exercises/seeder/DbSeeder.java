package com.futurespace.exercises.seeder;

import com.futurespace.exercises.model.Autor;
import com.futurespace.exercises.model.Editorial;
import com.futurespace.exercises.model.Libro;
import com.futurespace.exercises.model.Tematica;
import com.futurespace.exercises.repository.IAutorRepository;
import com.futurespace.exercises.repository.IEditorialRepository;
import com.futurespace.exercises.repository.ILibroRepository;
import com.futurespace.exercises.repository.ITematicaRepository;

import java.util.Arrays;
import java.util.List;

public class DbSeeder {
    ITematicaRepository tematicaRepository;
    ILibroRepository libroRepository;
    IEditorialRepository editorialRepository;
    IAutorRepository autorRepository;

    public DbSeeder(ITematicaRepository tematicaRepository,
                    IEditorialRepository editorialRepository,
                    IAutorRepository autorRepository,
                    ILibroRepository libroRepository) {
        this.autorRepository=autorRepository;
        this.editorialRepository=editorialRepository;
        this.libroRepository=libroRepository;
        this.tematicaRepository=tematicaRepository;
    }

    //Inserciones para manejar la base de datos con SpringJPA
    public void initDB(){
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

        /*Para evitar duplicidad en la base de datos como la que ocurriría, por ejemplo, si en el campo Tematica
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

    }
}
