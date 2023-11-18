package com.backend.enums.converters;

import java.util.stream.Stream;

import com.backend.enums.SimNao;

import jakarta.persistence.AttributeConverter;

public class SimNaoConverter implements AttributeConverter<SimNao, String> {

    @Override
    public String convertToDatabaseColumn(SimNao simNao) {
        if (simNao == null) {
            return null;
        }
        return simNao.getValor();
    }

    @Override
    public SimNao convertToEntityAttribute(String valor) {
        if (valor == null) {
            return null;
        }
        return Stream.of(SimNao.values())
                .filter(t -> t.getValor().equals(valor))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
