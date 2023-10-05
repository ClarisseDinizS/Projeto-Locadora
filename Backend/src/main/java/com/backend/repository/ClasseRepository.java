package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.Classe;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {

}
