package com.backend.dto;

import java.time.LocalDate;

import com.backend.model.Cliente;
import com.backend.model.Item;

import jakarta.validation.constraints.NotNull;

public record LocacaoDTO(Long id, @NotNull LocalDate dtLocacao, 
                @NotNull LocalDate dtDevolucaoPrevista,  @NotNull LocalDate dtDevolucaoEfetiva,
        @NotNull Double valorCobrado, @NotNull Double multaCobrada, @NotNull Item item, @NotNull Cliente cliente ) {

    
}
