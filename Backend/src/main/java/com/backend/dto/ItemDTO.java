package com.backend.dto;

import com.backend.model.Titulo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ItemDTO(Long id, @NotBlank @NotNull Integer numserie, LocalDate dtaquisicao, @NotBlank @NotNull String tipoItem,
                      @NotBlank @NotNull Titulo titulo) {
}
