package org.bedu.java.backend.Postwork.services;


import lombok.RequiredArgsConstructor;
import org.bedu.java.backend.Postwork.controllers.mappers.ClienteMapper;
import org.bedu.java.backend.Postwork.model.Cliente;
import org.bedu.java.backend.Postwork.persistence.IClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final IClienteRepository repository;
    private final ClienteMapper mapper;

    public Cliente saveCliente(Cliente cliente) {
        return mapper.clienteEntityToClienteModel(
               repository.save(mapper.clienteModelToClienteEntity(cliente))
        );
    }

    public List<Cliente> getClientes(){
        return repository.findAll().stream().map(cliente -> mapper.clienteEntityToClienteModel(cliente)).collect(Collectors.toList());
    }


    public Optional<Cliente> getCliente(long idCliente) {
       return repository.findById(idCliente)
                .map(cliente -> Optional.of(mapper.clienteEntityToClienteModel(cliente)))
                .orElse(Optional.empty());
    }

    public void deleteCliente(long idcliente){
        repository.deleteById(idcliente);
    }

    public Cliente updateCliente(Cliente cliente){
        return mapper.clienteEntityToClienteModel(
                repository.save(mapper.clienteModelToClienteEntity(cliente))
        );
    }

    public long countClientes(){
        return repository.count();
    }
}