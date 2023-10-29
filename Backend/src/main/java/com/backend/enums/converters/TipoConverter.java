package com.backend.enums.converters;

import java.util.stream.Stream;

import com.backend.enums.Tipo;

import jakarta.persistence.AttributeConverter;

public class TipoConverter implements AttributeConverter<Tipo, String> {

    @Override
    public String convertToDatabaseColumn(Tipo tipo) {
        if (tipo == null) {
            return null;
        }
        return tipo.getValor();
    }

    @Override
    public Tipo convertToEntityAttribute(String valor) {
        if (valor == null) {
            return null;
        }
        return Stream.of(Tipo.values())
                .filter(t -> t.getValor().equals(valor))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
