package com.backend.dto;

import java.time.LocalDate;

import com.backend.model.Titulo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemDTO(Long id, @NotNull Integer numSerie, LocalDate dtaAquisicao,
        @NotBlank @NotNull String tipoItem, @NotNull Titulo titulo) {
}
