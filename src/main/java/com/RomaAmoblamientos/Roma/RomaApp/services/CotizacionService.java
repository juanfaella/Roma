package com.RomaAmoblamientos.Roma.RomaApp.services;

import com.RomaAmoblamientos.Roma.RomaApp.entities.Cotizacion;
import com.RomaAmoblamientos.Roma.RomaApp.repositories.CotizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CotizacionService {
    @Autowired
    private CotizacionRepository cotizacionRepository;

    public List<Cotizacion> getAllCotizaciones() {
        return cotizacionRepository.findAll();
    }

    public Optional<Cotizacion> getCotizacionById(Long id) {
        return cotizacionRepository.findById(id);
    }

    public Cotizacion createOrUpdateCotizacion(Cotizacion cotizacion) {
        return cotizacionRepository.save(cotizacion);
    }

    public void deleteCotizacion(Long id) {
        cotizacionRepository.deleteById(id);
    }
    public List<Cotizacion> getLatestCotizaciones(int limit) {
        return cotizacionRepository.findAll(PageRequest.of(0, limit, Sort.by(Sort.Order.desc("id")))).getContent();
    }
}
