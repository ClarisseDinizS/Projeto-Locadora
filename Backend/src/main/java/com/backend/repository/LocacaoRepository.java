package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.Locacao;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

}