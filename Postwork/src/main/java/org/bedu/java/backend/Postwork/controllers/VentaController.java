package org.bedu.java.backend.Postwork.controllers;

import org.bedu.java.backend.Postwork.model.Venta;

import org.bedu.java.backend.Postwork.services.VentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/venta")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @GetMapping("/{ventaId}")//Obtener registro específico
    public ResponseEntity<Venta> getVenta(@PathVariable Long ventaId) {

        Optional<Venta> ventaDB = ventaService.getVenta(ventaId);

        if (ventaDB.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La venta buscada no existe.");
        }
        return ResponseEntity.ok(ventaDB.get());
    }

    @GetMapping//Obtener todos los registros
    public ResponseEntity<List<Venta>>getVentas(){
        return ResponseEntity.ok(ventaService.getVentas());
    }

    @PostMapping//Crear nuevo registro
    public ResponseEntity<Void> saveVenta(@RequestBody Venta venta) {
        Venta ventaNuevo = ventaService.saveVenta(venta);
        return ResponseEntity.created(URI.create(String.valueOf(ventaNuevo.getVentaId()))).build();
    }

    @PutMapping("/{VentaId}")//Actualizar registro
    public ResponseEntity<Void> updateVenta(@PathVariable Long ventaId, @RequestBody Venta venta) {

        ventaService.updateVenta(venta);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{VentaId}")//borrar registro
    public ResponseEntity<Void> deleteVenta(@PathVariable Long ventaId) {
        ventaService.deleteVenta(ventaId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
