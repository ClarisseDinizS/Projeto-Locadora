package com.backend.dto;

import java.time.LocalDate;

import com.backend.enums.Status;
import com.backend.model.Socio;

import jakarta.validation.constraints.NotNull;

public record DependenteDTO(Long id, @NotNull Integer numeroInscricao, @NotNull String nome,
        LocalDate dataNascimento, String sexo, Status estahAtivo, Socio socio) {
}
