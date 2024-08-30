package com.RomaAmoblamientos.Roma.RomaApp.repositories;

import com.RomaAmoblamientos.Roma.RomaApp.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
