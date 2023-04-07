package com.example.consumirapi.dominio.entidadesDominio.enumeracion;

public enum EstadoCategoria {
    ACTIVO(1),
    INACTIVO(0);

    private final int valor;

    EstadoCategoria(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
