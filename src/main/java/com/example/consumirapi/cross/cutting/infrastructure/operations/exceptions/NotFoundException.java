package com.example.consumirapi.cross.cutting.infrastructure.operations.exceptions;

public class NotFoundException extends RuntimeException{

    public static final String DESCRIPTION = "No se ha encontrado (404)";
    public NotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
