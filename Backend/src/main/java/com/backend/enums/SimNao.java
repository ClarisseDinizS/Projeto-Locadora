package com.backend.enums;

public enum SimNao {
    SIM("Sim"), NAO("Não");

    private String valor;

    private SimNao(String valor) {
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
