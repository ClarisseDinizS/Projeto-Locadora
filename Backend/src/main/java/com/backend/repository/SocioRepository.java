package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.Socio;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {

    @Query("SELECT s FROM Socio s WHERE s.estahAtivo = 'Sim'")
    List<Socio> findAllByEstahAtivo();

    @Query("SELECT s FROM Socio s WHERE s.estahAtivo = 'Não'")
    List<Socio> findAllByEstahInativo();
    
}
