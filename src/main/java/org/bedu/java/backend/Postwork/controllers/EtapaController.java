package org.bedu.java.backend.Postwork.controllers;

import lombok.RequiredArgsConstructor;
import org.bedu.java.backend.Postwork.model.Etapa;
import org.bedu.java.backend.Postwork.services.EtapaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/etapa")
@RequiredArgsConstructor
public class EtapaController {

    private final EtapaService etapaService;

    @GetMapping("/{etapaId}")//Obtener registro específico
    public ResponseEntity<Etapa> getEtapa(@PathVariable Long etapaId){
        Optional<Etapa> etapaDb = etapaService.getEtapa(etapaId);

        if (etapaDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la etapa buscada");
        }

        return ResponseEntity.ok(etapaDb.get());
    }

    @GetMapping
    public ResponseEntity <List<Etapa>> getEtapas(){
        return ResponseEntity.ok(etapaService.getEtapas());
    }

    @PostMapping
    public ResponseEntity<Void> saveEtapa(@RequestBody Etapa etapa){

        Etapa etapaNueva = etapaService.saveEtapa(etapa);

        return ResponseEntity.created(URI.create(String.valueOf(etapaNueva.getEtapaID()))).build();
    }

    @PutMapping("/{etapaId}")
    public ResponseEntity<Void> updateEtapa(@PathVariable Long etapaId, @RequestBody Etapa etapa){

        etapaService.updateEtapa(etapa);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{etapaId}")
    public ResponseEntity<Void> deleteEtapa(@PathVariable Long etapaId){

        etapaService.deleteEtapa(etapaId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}