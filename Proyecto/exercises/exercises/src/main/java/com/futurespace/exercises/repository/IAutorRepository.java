package com.futurespace.exercises.repository;

import com.futurespace.exercises.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Ejercicio3
@Repository
public interface IAutorRepository extends JpaRepository<Autor, Long> {

    //Método auxiliar que usaremos para crear los libros (y también es útil en general)
    //Spring JPA nos permite hacer este tipo de consultas de manera muy sencilla
    Optional<Autor> findByNombreAndApellidos(String nombre, String apellidos);
}
