package com.example.consumirapi.infraestructuraTransversal.operaciones.exceptions;

public class FieldAlreadyExistException extends ConflictException {

    public static final String DESCRIPTION = "Existe un registro con el mismo nombre ";
    public FieldAlreadyExistException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
