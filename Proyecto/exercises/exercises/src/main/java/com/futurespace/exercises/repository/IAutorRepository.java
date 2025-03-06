package com.futurespace.exercises.repository;

import com.futurespace.exercises.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Ejercicio2
@Repository
public interface IAutorRepository extends JpaRepository<Autor, Long> {
}
