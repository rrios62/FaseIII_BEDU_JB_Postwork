package org.bedu.java.backend.Postwork.persistence;

import org.bedu.java.backend.Postwork.persistence.entities.Etapa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEtapaRepository extends JpaRepository<Etapa, Long> {
}