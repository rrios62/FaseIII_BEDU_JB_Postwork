package org.bedu.java.backend.Postwork.controllers.mappers;

import org.bedu.java.backend.Postwork.persistence.entities.Etapa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EtapaMapper {
    Etapa etapaModelToEtapaEntity(org.bedu.java.backend.Postwork.model.Etapa etapaModel);
    org.bedu.java.backend.Postwork.model.Etapa etapaEntityToEtapaModel(Etapa etapa);

}
