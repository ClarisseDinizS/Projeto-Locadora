package com.backend.dto;

import java.time.LocalDate;
import java.util.List;

import com.backend.model.Ator;
import com.backend.model.Classe;
import com.backend.model.Diretor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TituloDTO(Long id, @NotBlank @NotNull String nome, LocalDate ano, String sinopse,
        @NotBlank @NotNull String categoria, @NotBlank @NotNull Diretor diretor,
        @NotBlank @NotNull Classe classe, @NotBlank @NotNull List<Ator> atores) {
}
