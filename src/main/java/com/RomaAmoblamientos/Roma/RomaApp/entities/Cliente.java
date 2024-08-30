package com.RomaAmoblamientos.Roma.RomaApp.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String empresa;

    @OneToMany(mappedBy = "cliente")
    private List<Cotizacion> cotizaciones;

    public void setId(Long id) {
        this.id = id;
    }
}
