package com.example.consumirapi.dominio.entities.domain.enumeration;

public enum EstadoSubCategoria {
    ACTIVO(1),
    INACTIVO(0);

    private final int valor;

    EstadoSubCategoria(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
