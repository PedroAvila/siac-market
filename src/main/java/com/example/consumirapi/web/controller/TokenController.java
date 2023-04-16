package com.example.consumirapi.web.controller;

import com.example.consumirapi.dominio.services.domain.interfaces.SdToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {

    @Autowired
    private SdToken sdToken;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String obtenerToken() {
        return sdToken.obtenerToken();
    }
}
