package com.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClienteDTO(Long id, @NotNull Integer numeroInscricao, @NotNull String nome,
                LocalDate dataNascimento, String sexo, @Pattern(regexp = "Sim|Não") String estahAtivo) {
}
