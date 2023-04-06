package com.example.consumirapi.infraestructuraTransversal.operaciones.exceptions;

public class FieldInvalidException extends BadRequestException {
    public static final String DESCRIPTION = "Campo no válido";
    public FieldInvalidException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
