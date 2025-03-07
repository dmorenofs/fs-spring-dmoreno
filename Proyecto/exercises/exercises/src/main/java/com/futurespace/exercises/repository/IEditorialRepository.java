package com.futurespace.exercises.repository;

import com.futurespace.exercises.model.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Ejercicio3
@Repository
public interface IEditorialRepository extends JpaRepository<Editorial, Long> {

    //Método auxiliar que usaremos para crear los libros (y también es útil en general)
    Optional<Editorial> findByNombre(String nombre);
}
