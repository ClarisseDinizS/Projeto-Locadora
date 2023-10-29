package com.backend.enums;

public enum Tipo {
    FITA("Fita"), DVD("DVD"), BLURAY("Blu-Ray");

    private String valor;

    private Tipo(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }

}
