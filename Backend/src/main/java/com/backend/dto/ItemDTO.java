package com.backend.dto;

import java.time.LocalDate;

import com.backend.enums.Tipo;
import com.backend.model.Titulo;

import jakarta.validation.constraints.NotNull;

public record ItemDTO(Long id, @NotNull Integer numSerie, LocalDate dtaAquisicao,
        @NotNull Tipo tipoItem, @NotNull Titulo titulo) {
}
