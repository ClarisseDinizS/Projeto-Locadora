package com.backend.dto;

import com.backend.model.Ator;
import com.backend.model.Classe;
import com.backend.model.Diretor;

import java.time.LocalDate;
import java.util.List;

public record TituloDTO(Long id, String nome, LocalDate ano, String sinopse, String categoria,
                        Diretor diretor, Classe classe, List<Ator> atores  ) {
}
