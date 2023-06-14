package org.bedu.java.backend.Postwork.persistence;


import org.bedu.java.backend.Postwork.persistence.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {

}