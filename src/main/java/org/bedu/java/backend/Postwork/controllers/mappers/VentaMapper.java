package org.bedu.java.backend.Postwork.controllers.mappers;

import org.bedu.java.backend.Postwork.persistence.entities.Venta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VentaMapper {
    Venta ventaModelToVentaEntity(org.bedu.java.backend.Postwork.model.Venta ventaModel);
    org.bedu.java.backend.Postwork.model.Venta ventaEntityToVentaModel(Venta venta);
}
