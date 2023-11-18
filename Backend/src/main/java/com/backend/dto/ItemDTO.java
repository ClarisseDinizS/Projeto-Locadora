package com.backend.dto;

import java.time.LocalDate;

import com.backend.model.Titulo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ItemDTO(Long id, @NotNull Integer numSerie, LocalDate dtaAquisicao,
        @Pattern(regexp = "Fita|DVD|Blu-Ray") String tipoItem, @NotNull Titulo titulo) {
}
