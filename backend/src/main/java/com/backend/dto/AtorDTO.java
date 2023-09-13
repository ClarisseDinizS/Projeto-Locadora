package com.backend.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtorDTO(Long id, @NotBlank @NotNull @Length(min = 5, max = 100) String nome) {
        
}
