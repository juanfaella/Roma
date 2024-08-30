package com.RomaAmoblamientos.Roma.RomaApp.entities;

import jakarta.persistence.*;

import java.util.Date;
@Entity
public class Cotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private Double presupuesto;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private String descripcion;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
    private Double manoDeObra;
    private Double total;

    public void setId(Long id) {
        this.id = id;
    }
}
