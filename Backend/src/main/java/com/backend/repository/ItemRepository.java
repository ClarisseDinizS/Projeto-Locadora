package com.backend.repository;

import com.backend.model.Item;
import com.backend.model.Titulo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByTitulo(Titulo titulo);
}
