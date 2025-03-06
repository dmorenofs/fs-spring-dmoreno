package com.futurespace.exercises.repository;

import com.futurespace.exercises.model.Tematica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Ejercicio2
@Repository
public interface ITematicaRepository extends JpaRepository<Tematica, Long> {
}
