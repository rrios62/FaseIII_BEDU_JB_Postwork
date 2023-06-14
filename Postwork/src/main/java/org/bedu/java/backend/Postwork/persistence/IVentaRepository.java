package org.bedu.java.backend.Postwork.persistence;

import org.bedu.java.backend.Postwork.persistence.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVentaRepository extends JpaRepository<Venta, Long> {
}