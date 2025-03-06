package com.futurespace.exercises.repository;

import com.futurespace.exercises.model.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Ejercicio2
@Repository
public interface IEditorialRepository extends JpaRepository<Editorial, Long> {
}
