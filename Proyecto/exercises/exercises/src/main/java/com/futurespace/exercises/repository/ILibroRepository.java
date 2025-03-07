package com.futurespace.exercises.repository;

import com.futurespace.exercises.model.Editorial;
import com.futurespace.exercises.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//Ejercicio3
@Repository
public interface ILibroRepository extends JpaRepository<Libro, Long> {

    //Ejercicio4
    @Query("SELECT l FROM Libro l WHERE l.añoPublicacion > 2001")
    List<Libro> findBooksPublishedAfter2001QUERY();

    /*Con intelliJ no hace ni falta revisar la documentación, ya que te va marcando las posibilidades
    * según vas escribiendo el nombre del método, pero siempre está bien, concretamente este link fue muy
    * útil: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html*/
    //Ejercicio5
    List<Libro> findByAñoPublicacionGreaterThan(int año);

    //Ejercicio 6.1
    List<Libro> findByAñoPublicacionIs(int año);

    //Ejercicio 6.2
    List<Libro> findByIsbnIs(String isbn);

    //Ejercicio 6.3
    List<Libro> findByEditorial_Nombre(String editorial);

    //Ejercicio 6.4
    List<Libro> findByAñoPublicacionIsAndEditorial_Nombre(int año, String editorial);

}
