package com.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClienteDTO(Long id, @NotBlank @NotNull Integer numeroInscricao, @NotBlank @NotNull String nome,
                         LocalDate dataNascimento, String sexo, String estahAtivo ) {
}
