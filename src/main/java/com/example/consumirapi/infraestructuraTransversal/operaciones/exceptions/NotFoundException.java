package com.example.consumirapi.infraestructuraTransversal.operaciones.exceptions;

public class NotFoundException extends RuntimeException{

    public static final String DESCRIPTION = "No se ha encontrado (404)";
    public NotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
