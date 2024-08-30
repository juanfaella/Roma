package com.RomaAmoblamientos.Roma.RomaApp.services;

import com.RomaAmoblamientos.Roma.RomaApp.entities.Categoria;
import com.RomaAmoblamientos.Roma.RomaApp.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> getCategoriaById(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria createOrUpdateCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void deleteCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
    public List<Categoria> getLatestCategorias(int limit) {
        return categoriaRepository.findAll(PageRequest.of(0, limit, Sort.by(Sort.Order.desc("id")))).getContent();
    }
}
