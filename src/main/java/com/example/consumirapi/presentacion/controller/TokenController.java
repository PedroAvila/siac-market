package com.example.consumirapi.presentacion.controller;

import com.example.consumirapi.aplicacion.serviciosDistribuido.ITokenAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class TokenController {

    @Autowired
    private ITokenAccessService tokenAccessService;
    @PostMapping("/obtenerToken")
    public String obtenerToken(){
        String response = tokenAccessService.obtenerToken();
        return response;
    }
}
