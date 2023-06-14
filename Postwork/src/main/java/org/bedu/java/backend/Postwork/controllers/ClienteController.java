package org.bedu.java.backend.Postwork.controllers;

import org.bedu.java.backend.Postwork.model.Cliente;
import org.bedu.java.backend.Postwork.services.ClienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping("/{clienteId}")//Obtener registro específico
    public ResponseEntity<Cliente> getCliente(@PathVariable Long clienteId) {

        Optional<Cliente> clienteDb = clienteService.getCliente(clienteId);

        if (clienteDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el cliente.");
        }
        return ResponseEntity.ok(clienteDb.get());
    }

    @GetMapping//Obtener todos los registros
    public ResponseEntity<List<Cliente>> getClientes() {
        return ResponseEntity.ok(clienteService.getClientes());
    }

    @PostMapping//Crear nuevo registro
    public ResponseEntity<Void> saveCliente(@RequestBody Cliente cliente) {
            Cliente clienteNuevo = clienteService.saveCliente(cliente);
        return ResponseEntity.created(URI.create(String.valueOf(clienteNuevo.getId()))).build();
    }

    @PutMapping("/{clienteId}")//Actualizar registro
    public ResponseEntity<Void> updateCliente(@PathVariable Long clienteId, @RequestBody Cliente cliente) {

        clienteService.updateCliente(cliente);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{clienteId}")//borrar registro
    public ResponseEntity<Void> deleteCliente(@PathVariable Long clienteId) {
        clienteService.deleteCliente(clienteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}