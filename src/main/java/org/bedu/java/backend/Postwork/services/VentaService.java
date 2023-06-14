package org.bedu.java.backend.Postwork.services;

import lombok.RequiredArgsConstructor;
import org.bedu.java.backend.Postwork.controllers.mappers.VentaMapper;
import org.bedu.java.backend.Postwork.model.Venta;
import org.bedu.java.backend.Postwork.persistence.IVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VentaService {
    private final IVentaRepository repository;
    private final VentaMapper mapper;

    public Venta saveVenta(Venta Venta) {
        return mapper.ventaEntityToVentaModel(
                repository.save(mapper.ventaModelToVentaEntity(Venta))
        );
    }

    public List<Venta> getVentas(){
        return repository.findAll().stream().map(venta -> mapper.ventaEntityToVentaModel(venta)).collect(Collectors.toList());
    }

    public Optional<Venta> getVenta(long idVenta) {
        return repository.findById(idVenta)
                .map(venta -> Optional.of(mapper.ventaEntityToVentaModel(venta)))
                .orElse(Optional.empty());
    }

    public void deleteVenta(long idVenta){
        repository.deleteById(idVenta);
    }

    public Venta updateVenta(Venta venta){
        return mapper.ventaEntityToVentaModel(
                repository.save(mapper.ventaModelToVentaEntity(venta))
        );
    }

    public long countVentas(){
        return repository.count();
    }
}
