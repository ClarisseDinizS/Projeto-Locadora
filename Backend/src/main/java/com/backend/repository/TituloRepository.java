package com.backend.repository;

import com.backend.model.Ator;
import com.backend.model.Classe;
import com.backend.model.Diretor;
import com.backend.model.Titulo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {

    List<Titulo> findByDiretor(Diretor diretor);

    List<Titulo> findByAtores(Ator ator);

    List<Titulo> findByClasse(Classe classe);
}
