package com.futurespace.exercises.model;

import jakarta.persistence.*;

import java.util.List;

//Ejercicio 2
@Entity
@Table(name = "Editoriales")
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "razon_social", length = 250)
    private String razonSocial;

    public Editorial() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }




    public Editorial(String nombre, String razonSocial) {
        this.nombre = nombre;
        this.razonSocial = razonSocial;
    }
}
