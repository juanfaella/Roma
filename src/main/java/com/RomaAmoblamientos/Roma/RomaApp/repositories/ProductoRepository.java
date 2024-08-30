package com.RomaAmoblamientos.Roma.RomaApp.repositories;

import com.RomaAmoblamientos.Roma.RomaApp.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
