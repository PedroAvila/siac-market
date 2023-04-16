package com.example.consumirapi.cross.cutting.infrastructure.operations.exceptions;

public class BadRequestException extends RuntimeException {

    private static final String DESCRIPTION = "Bad Request Exception (400)";

    public BadRequestException(String detail) { super(DESCRIPTION + ". " + detail); }
}
