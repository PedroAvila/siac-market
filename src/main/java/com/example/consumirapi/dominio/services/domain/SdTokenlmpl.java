package com.example.consumirapi.dominio.services.domain;

import com.example.consumirapi.dominio.services.domain.interfaces.SdToken;
import com.example.consumirapi.persistence.agent.services.TokenAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SdTokenlmpl implements SdToken {

    @Autowired
    private TokenAccessService tokenAccessService;
    @Override
    public String obtenerToken() {
        return this.tokenAccessService.obtenerToken();
    }
}
