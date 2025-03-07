package com.futurespace.exercises.repository;

import com.futurespace.exercises.model.Tematica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Ejercicio3
@Repository
public interface ITematicaRepository extends JpaRepository<Tematica, Long> {

    //Método auxiliar que usaremos para crear los libros (y también es útil en general)
    Optional<Tematica> findByCategoria(String categoria);
}
