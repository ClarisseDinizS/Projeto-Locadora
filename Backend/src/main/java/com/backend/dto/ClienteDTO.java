package com.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record ClienteDTO(Long id, @NotNull Integer numeroInscricao, @NotNull String nome,
                LocalDate dataNascimento, String sexo, String estahAtivo) {
}
