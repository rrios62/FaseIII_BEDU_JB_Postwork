package org.bedu.java.backend.Postwork.services;

import lombok.RequiredArgsConstructor;
import org.bedu.java.backend.Postwork.controllers.mappers.VisitaMapper;
import org.bedu.java.backend.Postwork.model.Visita;
import org.bedu.java.backend.Postwork.persistence.IVisitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitaService {
    private final IVisitaRepository repository;
    private final VisitaMapper mapper;

    public Visita saveVisita(Visita visita) {
        return mapper.visitaEntityToVisitaModel(
                repository.save(mapper.visitaModelToVisitaEntity(visita))
        );
    }

    public List<Visita> getVisitas(){
        return repository.findAll().stream().map(visita -> mapper.visitaEntityToVisitaModel(visita)).collect(Collectors.toList());
    }

    public Optional<Visita> getVisita(long idVisita) {
        return repository.findById(idVisita)
                .map(Visita -> Optional.of(mapper.visitaEntityToVisitaModel(Visita)))
                .orElse(Optional.empty());
    }

    public void deleteisita(long idVisita){
        repository.deleteById(idVisita);
    }

    public Visita updateVisita(Visita visita){
        return mapper.visitaEntityToVisitaModel(
                repository.save(mapper.visitaModelToVisitaEntity(visita))
        );
    }

    public long countVisitas(){
        return repository.count();
    }
}
