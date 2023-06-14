package org.bedu.java.backend.Postwork.controllers;

import lombok.RequiredArgsConstructor;
import org.bedu.java.backend.Postwork.model.Producto;
import org.bedu.java.backend.Postwork.services.ProductoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/{ProductoId}")//Obtener registro específico
    public ResponseEntity<Producto> getProducto(@PathVariable Long productoId) {
        Optional<Producto> productDb = productoService.getProducto(productoId);

        if (productDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El producto solicitado no existe.");
        }
        return ResponseEntity.ok(productDb.get());
    }

    @GetMapping//Obtener todos los registros
    public ResponseEntity<List<Producto>>getProductos(){
        return ResponseEntity.ok(productoService.getProductos());
    }

    @PostMapping//Crear nuevo registro
    public ResponseEntity<Void> saveProducto(@RequestBody Producto producto) {
        Producto productoNuevo = productoService.saveProducto(producto);
        return ResponseEntity.created(URI.create(String.valueOf(productoNuevo.getId()))).build();
    }

    @PutMapping("/{ProductoId}")//Actualizar registro
    public ResponseEntity<Void> updateProducto(@PathVariable Long productoId, @RequestBody Producto producto) {

        productoService.updateProducto(producto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{ProductoId}")//borrar registro
    public ResponseEntity<Void> deleteProducto(@PathVariable Long productoId) {
        productoService.deleteProducto(productoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
