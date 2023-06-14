package org.bedu.java.backend.Postwork.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.bedu.java.backend.Postwork.persistence.entities.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Long> {
}