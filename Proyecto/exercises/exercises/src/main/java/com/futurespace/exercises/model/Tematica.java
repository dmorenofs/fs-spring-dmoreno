package com.futurespace.exercises.model;

import jakarta.persistence.*;

import java.util.List;

//Ejercicio 2
@Entity
@Table(name = "Tematicas")
public class Tematica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "categoria", nullable = false, length = 50, unique = true)
    private String categoria;

    public Tematica() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }



    public Tematica(String categoria) {
        this.categoria = categoria;
    }
}
