package com.futurespace.exercises.model;

import jakarta.persistence.*;

import java.time.Year;

//Ejercicio 2
@Entity
@Table(name = "Libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "isbn", nullable = false, length = 20, unique = true)
    private String isbn;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "ano_publicacion", nullable = false)
    private int añoPublicacion;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "editorial_id", nullable = false)
    private Editorial editorial;

    @ManyToOne
    @JoinColumn(name = "tematica_id", nullable = false)
    private Tematica tematica;

    public Libro() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setAñoPublicacion(int añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Tematica getTematica() {
        return tematica;
    }

    public void setTematica(Tematica tematica) {
        this.tematica = tematica;
    }

    public Libro(String isbn, String titulo, int añoPublicacion, Autor autor, Editorial editorial, Tematica tematica) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.añoPublicacion = añoPublicacion;
        this.autor = autor;
        this.editorial = editorial;
        this.tematica = tematica;
    }
}
