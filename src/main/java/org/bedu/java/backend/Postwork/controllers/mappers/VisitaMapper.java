package org.bedu.java.backend.Postwork.controllers.mappers;

import org.bedu.java.backend.Postwork.persistence.entities.Visita;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitaMapper {
    Visita visitaModelToVisitaEntity(org.bedu.java.backend.Postwork.model.Visita visitaModel);
    org.bedu.java.backend.Postwork.model.Visita visitaEntityToVisitaModel(Visita visita);
}
