package com.example.consumirapi.cross.cutting.infrastructure.operations.exceptions;

public class ForbiddenException extends RuntimeException {

    public static final String DESCRIPTION = "Prohibido (403)";

    public ForbiddenException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
