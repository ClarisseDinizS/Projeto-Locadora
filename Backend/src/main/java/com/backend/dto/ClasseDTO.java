package com.backend.dto;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClasseDTO(Long id, @NotBlank @NotNull @Length(min = 5, max = 100) String nome,
                @NotNull Double valor, @NotNull LocalDate data) {

}
