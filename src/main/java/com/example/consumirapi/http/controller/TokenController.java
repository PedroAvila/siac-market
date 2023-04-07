package com.example.consumirapi.http.controller;

import com.example.consumirapi.aplicacion.accessService.ITokenAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TokenController {

    @Autowired
    private ITokenAccessService tokenAccessService;
    @PostMapping("/obtenerToken")
    public String obtenerToken(){
        return tokenAccessService.obtenerToken();
    }
}
