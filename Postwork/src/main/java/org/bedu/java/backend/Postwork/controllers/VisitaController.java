package org.bedu.java.backend.Postwork.controllers;

import org.bedu.java.backend.Postwork.model.Cliente;
import org.bedu.java.backend.Postwork.model.Visita;

import org.bedu.java.backend.Postwork.services.VisitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Visita")
@RequiredArgsConstructor
public class VisitaController {

    private final VisitaService visitaService;

    @GetMapping("/{VisitaId}")//Obtener registro específico
    public ResponseEntity<Visita> getVisita(@PathVariable Long visitaId) {

        Optional<Visita> visitaDb = visitaService.getVisita(visitaId);

        if (visitaDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe visita programada.");
        }
        return ResponseEntity.ok(visitaDb.get());
    }

    @GetMapping//Obtener todos los registros
    public ResponseEntity<List<Visita>>getVisitas(){
        return ResponseEntity.ok(visitaService.getVisitas());
    }

    @PostMapping//Crear nuevo registro
    public ResponseEntity<Void> saveVisita(@RequestBody Visita visita) {
            Visita visitaNuevo = visitaService.saveVisita(visita);
        return ResponseEntity.created(URI.create(String.valueOf(visitaNuevo.getId()))).build();
    }

    @PutMapping("/{VisitaId}")//Actualizar registro
    public ResponseEntity<Void> updateVisita(@PathVariable Long visitaId, @RequestBody Visita visita) {

        visitaService.updateVisita(visita);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{VisitaId}")//borrar registro
    public ResponseEntity<Void> deleteVisita(@PathVariable Long visitaId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
