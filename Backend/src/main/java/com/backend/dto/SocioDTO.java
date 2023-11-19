package com.backend.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SocioDTO(Long id, @NotNull Integer numeroInscricao, @NotNull String nome,
                LocalDate dataNascimento, String sexo, @Pattern(regexp = "Sim|Não") String estahAtivo,
                String cpf, String endereco, String telefone, List<ClienteDTO> dependentes) {
}
