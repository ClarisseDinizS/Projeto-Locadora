package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.Socio;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {
}
