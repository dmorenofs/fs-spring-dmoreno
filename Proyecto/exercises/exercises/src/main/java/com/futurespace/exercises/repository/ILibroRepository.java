package com.futurespace.exercises.repository;

import com.futurespace.exercises.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Ejercicio3
@Repository
public interface ILibroRepository extends JpaRepository<Libro, Long> {

    //Ejercicio4
    @Query("SELECT l FROM Libro l WHERE l.anioPublicacion > 2001")
    List<Libro> findBooksPublishedAfter2001QUERY();

    /*Con intelliJ no hace ni falta revisar la documentación, ya que te va marcando las posibilidades
    * según vas escribiendo el nombre del método, pero siempre está bien, concretamente estos links fueron muy
    * útiles: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    * https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa*/
    //Ejercicio5
    List<Libro> findByAnioPublicacionGreaterThan(Integer year);

    //Ejercicio 6.1
    List<Libro> findByAnioPublicacionIs(Integer year);

    //Ejercicio 6.2
    Optional<Libro> findByIsbnIs(String isbn);

    //Ejercicio 6.3
    List<Libro> findByEditorial_Nombre(String editorial);

    //Ejercicio 6.4
    List<Libro> findByAnioPublicacionIsAndEditorial_Nombre(Integer year, String editorial);

}
