package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.Locacao;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

}