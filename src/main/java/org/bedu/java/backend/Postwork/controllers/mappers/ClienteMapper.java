package org.bedu.java.backend.Postwork.controllers.mappers;

import org.bedu.java.backend.Postwork.persistence.entities.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente clienteModelToClienteEntity(org.bedu.java.backend.Postwork.model.Cliente clienteModel);
    org.bedu.java.backend.Postwork.model.Cliente clienteEntityToClienteModel(Cliente cliente);
}
