package com.backend.enums.converters;

import java.util.stream.Stream;

import com.backend.enums.Status;

import jakarta.persistence.AttributeConverter;

public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }
        return status.getValor();
    }

    @Override
    public Status convertToEntityAttribute(String valor) {
        if (valor == null) {
            return null;
        }
        return Stream.of(Status.values())
                .filter(t -> t.getValor().equals(valor))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
