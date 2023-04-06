package com.example.consumirapi.infraestructuraTransversal.operaciones.exceptions;

public class MalformedHeaderException  extends BadRequestException {

    public static final String DESCRIPTION = "Token with wrong format";
    public MalformedHeaderException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
