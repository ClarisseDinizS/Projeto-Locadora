package com.backend.dto;

import java.time.LocalDate;
import java.util.List;

import com.backend.enums.Status;
import com.backend.model.Dependente;

import jakarta.validation.constraints.NotNull;

public record SocioDTO(Long id, @NotNull Integer numeroInscricao, @NotNull String nome,
        LocalDate dataNascimento, String sexo, Status estahAtivo, String cpf,
        String endereco, String telefone, List<Dependente> dependentes) {
}
