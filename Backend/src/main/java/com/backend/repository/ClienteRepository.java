package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE NOT EXISTS  " +
            "(SELECT s FROM Socio s WHERE s.id = c.id)")
    List<Cliente> findAllClientesQueNaoSaoSocios();

    @Query("SELECT c FROM Cliente c WHERE c.estahAtivo = 'Sim'")
    List<Cliente> findAllByEstahAtivo();
}
