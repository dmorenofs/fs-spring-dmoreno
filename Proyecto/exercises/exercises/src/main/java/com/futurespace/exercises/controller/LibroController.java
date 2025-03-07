package com.futurespace.exercises.controller;


import com.futurespace.exercises.model.Libro;
import com.futurespace.exercises.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*Para diferenciarlo de los ejercicios del curso de Spring, donde la URL era simplemente
* /users, he decidido añadir /api/ al principio, para que no haya confusión.
* Si la url tiene /api, trabajamos en el proyecto de la biblioteca JPA*/
@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final ILibroService libroService;

    @Autowired
    public LibroController(ILibroService libroService){
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<Libro>> getLibros(@RequestParam(required = false) Integer year){
        if (year != null){
            return ResponseEntity.ok(libroService.getLibrosByYear(year));
        }
        return ResponseEntity.ok(libroService.getAllLibros());
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Libro> getLibroByIsbn(@PathVariable String isbn){
        return libroService.getLibroByIsbn(isbn)
                .map(libro -> new ResponseEntity<>(libro, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/editorial/{editorialname}")
    public ResponseEntity<List<Libro>> getLibrosByEditorial(@PathVariable String editorialname, @RequestParam(required = false) Integer year){
        if (year != null){
            return ResponseEntity.ok(libroService.getLibrosByEditorialAndYear(year, editorialname));
        }
        return ResponseEntity.ok(libroService.getLibrosByEditorial(editorialname));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibro(@PathVariable Long id){
        return libroService.getLibroById(id)
                .map(libro -> new ResponseEntity<>(libro, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Libro> createLibro(@RequestBody Libro libro){
        return new ResponseEntity<>(libroService.saveLibro(libro),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libro){
        return  libroService.getLibroById(id)
                .map(existingLibro -> {
                    libro.setId(id);
                    return ResponseEntity.ok(libroService.saveLibro(libro));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id){
        if (libroService.getLibroById(id).isPresent()){
            libroService.deleteLibro(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
