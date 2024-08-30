package com.RomaAmoblamientos.Roma.RomaApp.controllers;

import com.RomaAmoblamientos.Roma.RomaApp.entities.Cotizacion;
import com.RomaAmoblamientos.Roma.RomaApp.services.CotizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizacionController {
    @Autowired
    private CotizacionService cotizacionService;

    @GetMapping
    public List<Cotizacion> getAllCotizaciones() {
        return cotizacionService.getAllCotizaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cotizacion> getCotizacionById(@PathVariable Long id) {
        return cotizacionService.getCotizacionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cotizacion createCotizacion(@RequestBody Cotizacion cotizacion) {
        return cotizacionService.createOrUpdateCotizacion(cotizacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cotizacion> updateCotizacion(@PathVariable Long id, @RequestBody Cotizacion cotizacion) {
        return cotizacionService.getCotizacionById(id)
                .map(existingCotizacion -> {
                    cotizacion.setId(id);
                    return ResponseEntity.ok(cotizacionService.createOrUpdateCotizacion(cotizacion));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/latest")
    public List<Cotizacion> getLatestCotizaciones(@RequestParam(defaultValue = "5") int limit) {
        return cotizacionService.getLatestCotizaciones(limit);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCotizacion(@PathVariable Long id) {
        if (cotizacionService.getCotizacionById(id).isPresent()) {
            cotizacionService.deleteCotizacion(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
