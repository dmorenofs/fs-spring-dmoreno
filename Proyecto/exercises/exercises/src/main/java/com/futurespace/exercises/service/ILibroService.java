package com.futurespace.exercises.service;

import com.futurespace.exercises.model.Libro;
import java.util.List;
import java.util.Optional;

public interface ILibroService {
    List<Libro> getAllLibros();
    Optional<Libro> getLibroById(Long id);
    List<Libro> getLibrosByYear(Integer year);
    Optional<Libro> getLibroByIsbn(String isbn);
    List<Libro> getLibrosByEditorial(String editorial);
    List<Libro> getLibrosByEditorialAndYear(Integer year, String editorial);
    Libro saveLibro(Libro libro);
    void deleteLibro(Long id);

}
