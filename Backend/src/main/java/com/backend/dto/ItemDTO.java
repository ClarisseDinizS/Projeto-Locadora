package com.backend.dto;

import java.time.LocalDate;

import com.backend.model.Titulo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemDTO(Long id, @NotBlank @NotNull Integer numserie, LocalDate dtaquisicao,
        @NotBlank @NotNull String tipoItem, @NotBlank @NotNull Titulo titulo) {
}
