package com.example.consumirapi.cross.cutting.infrastructure.operations.exceptions;

public class UnauthorizedException extends RuntimeException{

    public static final String DESCRIPTION = "Sin autorización (401)";
    public UnauthorizedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
