package com.futurespace.exercises.repository;

import com.futurespace.exercises.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Ejercicio2
@Repository
public interface ILibroRepository extends JpaRepository<Libro, Long> {
}
