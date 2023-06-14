package org.bedu.java.backend.Postwork.controllers.mappers;

import org.bedu.java.backend.Postwork.persistence.entities.Producto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    Producto productoModelToProductoEntity(org.bedu.java.backend.Postwork.model.Producto productoModel);
    org.bedu.java.backend.Postwork.model.Producto productoEntityToProductoModel(Producto producto);
}
