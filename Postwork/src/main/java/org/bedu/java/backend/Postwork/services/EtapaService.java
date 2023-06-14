package org.bedu.java.backend.Postwork.services;

import lombok.RequiredArgsConstructor;
import org.bedu.java.backend.Postwork.controllers.mappers.EtapaMapper;
import org.bedu.java.backend.Postwork.model.Etapa;
import org.bedu.java.backend.Postwork.persistence.IEtapaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EtapaService {
    private final IEtapaRepository repository;
    private final EtapaMapper mapper;

    public Etapa saveEtapa(Etapa etapa) {
        return mapper.etapaEntityToEtapaModel(
                repository.save(mapper.etapaModelToEtapaEntity(etapa))
        );
    }

    public List<Etapa> getEtapas(){
        return repository.findAll().stream().map(etapa -> mapper.etapaEntityToEtapaModel(etapa)).collect(Collectors.toList());
    }

    public Optional<Etapa> getEtapa(long idEtapa) {
        return repository.findById(idEtapa)
                .map(Etapa -> Optional.of(mapper.etapaEntityToEtapaModel(Etapa)))
                .orElse(Optional.empty());
    }

    public void deleteEtapa(long idEtapa){
        repository.deleteById(idEtapa);
    }

    public Etapa updateEtapa(Etapa etapa){
        return mapper.etapaEntityToEtapaModel(
                repository.save(mapper.etapaModelToEtapaEntity(etapa))
        );
    }

    public long cuenteEtapas(){
        return repository.count();
    }
}