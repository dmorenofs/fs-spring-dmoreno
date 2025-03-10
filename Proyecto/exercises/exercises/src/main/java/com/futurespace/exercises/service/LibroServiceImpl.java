package com.futurespace.exercises.service;

import com.futurespace.exercises.model.Libro;
import com.futurespace.exercises.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements ILibroService{

    private final ILibroRepository libroRepository;

    @Autowired
    public LibroServiceImpl(ILibroRepository libroRepository){
        this.libroRepository = libroRepository;
    }
    @Override
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    @Override
    public Optional<Libro> getLibroById(Long id) {
        return libroRepository.findById(id);
    }

    @Override
    public List<Libro> getLibrosByYear(Integer year) {
        return libroRepository.findByAnioPublicacionIs(year);
    }

    @Override
    public Optional<Libro> getLibroByIsbn(String isbn) {
        return libroRepository.findByIsbnIs(isbn);
    }

    @Override
    public List<Libro> getLibrosByEditorial(String editorial) {
        return libroRepository.findByEditorial_Nombre(editorial);
    }

    @Override
    public List<Libro> getLibrosByEditorialAndYear(Integer year, String editorial) {
        return libroRepository.findByAnioPublicacionIsAndEditorial_Nombre(year,editorial);
    }

    @Override
    public Libro saveLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public void deleteLibro(Long id) {
        libroRepository.deleteById(id);

    }
}
