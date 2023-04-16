package com.example.consumirapi.cross.cutting.infrastructure.operations.exceptions;

public class FieldInvalidException extends BadRequestException {
    public static final String DESCRIPTION = "Campo no válido";
    public FieldInvalidException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
