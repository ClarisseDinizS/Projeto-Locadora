package com.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteDTO(Long id, @NotBlank @NotNull Integer numeroInscricao, @NotBlank @NotNull String nome,
                LocalDate dataNascimento, String sexo, String estahAtivo) {
}
