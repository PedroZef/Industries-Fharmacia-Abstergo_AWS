package com.abstergo.fharmacia.repository;

import com.abstergo.fharmacia.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
