package com.example.consumirapi.cross.cutting.infrastructure.operations.exceptions;

public class ConflictException extends RuntimeException{

    public static final String DESCRIPTION = "Conflicto (409)";
    public ConflictException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
