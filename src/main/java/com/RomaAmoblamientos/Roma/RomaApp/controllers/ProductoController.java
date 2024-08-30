package com.RomaAmoblamientos.Roma.RomaApp.controllers;

import com.RomaAmoblamientos.Roma.RomaApp.entities.Categoria;
import com.RomaAmoblamientos.Roma.RomaApp.entities.Producto;
import com.RomaAmoblamientos.Roma.RomaApp.services.CategoriaService;
import com.RomaAmoblamientos.Roma.RomaApp.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return productoService.getProductoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createProducto(@RequestBody ProductoDTO productoDTO) {
        if (productoDTO.getNombre() == null || productoDTO.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del producto es obligatorio");
        }

        Categoria categoria = categoriaService.getCategoriaById(productoDTO.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setCantidadStock(productoDTO.getCantidadStock());
        producto.setMedidas(productoDTO.getMedidas());
        producto.setCategoria(categoria);  // Establece la categoría directamente

        Producto createdProducto = productoService.createOrUpdateProducto(producto);
        return ResponseEntity.ok(createdProducto);
    }

    @GetMapping("/latest")
    public List<Producto> getLatestProductos(@RequestParam(defaultValue = "5") int limit) {
        return productoService.getLatestProductos(limit);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        return productoService.getProductoById(id)
                .map(existingProducto -> {
                    existingProducto.setNombre(productoDTO.getNombre());
                    existingProducto.setDescripcion(productoDTO.getDescripcion());
                    existingProducto.setPrecio(productoDTO.getPrecio());
                    existingProducto.setCantidadStock(productoDTO.getCantidadStock());
                    existingProducto.setMedidas(productoDTO.getMedidas());

                    if (productoDTO.getCategoriaId() != null) {
                        Categoria categoria = categoriaService.getCategoriaById(productoDTO.getCategoriaId()).orElse(null);
                        existingProducto.setCategoria(categoria);
                    }

                    Producto updatedProducto = productoService.createOrUpdateProducto(existingProducto);
                    return ResponseEntity.ok(updatedProducto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (productoService.getProductoById(id).isPresent()) {
            productoService.deleteProducto(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/adjust-stock")
    public ResponseEntity<Producto> adjustStock(@PathVariable Long id, @RequestParam int cantidad) {
        productoService.adjustStock(id, cantidad);
        return ResponseEntity.ok(productoService.getProductoById(id).orElseThrow());
    }
}
