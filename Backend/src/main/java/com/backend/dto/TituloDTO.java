package com.backend.dto;

import java.time.Year;
import java.util.List;

import com.backend.model.Ator;
import com.backend.model.Classe;
import com.backend.model.Diretor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TituloDTO(Long id, @NotBlank @NotNull String nome, Year ano, String sinopse,
        @NotBlank @NotNull String categoria, @NotNull Diretor diretor,
        @NotNull Classe classe, @NotNull List<Ator> atores) {
}
