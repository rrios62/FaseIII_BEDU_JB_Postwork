package org.bedu.java.backend.Postwork.services;

import lombok.RequiredArgsConstructor;
import org.bedu.java.backend.Postwork.controllers.mappers.ProductoMapper;
import org.bedu.java.backend.Postwork.model.Producto;
import org.bedu.java.backend.Postwork.persistence.IProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final IProductoRepository repository;
    private final ProductoMapper mapper;

    public Producto saveProducto(Producto producto) {
        return mapper.productoEntityToProductoModel(
                repository.save(mapper.productoModelToProductoEntity(producto))
        );
    }

    public List<Producto> getProductos(){
        return repository.findAll().stream().map(Producto -> mapper.productoEntityToProductoModel(Producto)).collect(Collectors.toList());
    }

    public Optional<Producto> getProducto(long idProducto) {
        return repository.findById(idProducto)
                .map(producto -> Optional.of(mapper.productoEntityToProductoModel(producto)))
                .orElse(Optional.empty());
    }

    public void deleteProducto(long idProducto){
        repository.deleteById(idProducto);
    }

    public Producto updateProducto(Producto producto){
        return mapper.productoEntityToProductoModel(
                repository.save(mapper.productoModelToProductoEntity(producto))
        );
    }

    public long countProductos(){
        return repository.count();
    }
}
