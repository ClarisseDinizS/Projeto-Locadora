package com.backend.dto;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClasseDTO(Long id, @NotBlank @NotNull @Length(min = 5, max = 100) String nome, 
        @NotBlank @NotNull Integer valor, @NotBlank @NotNull String data) {
        
}
