package org.bedu.java.backend.Postwork.persistence;

import org.bedu.java.backend.Postwork.persistence.entities.Visita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVisitaRepository extends JpaRepository<Visita, Long> {
}