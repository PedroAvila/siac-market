package com.example.consumirapi.dominio.serviciosDominio;

import com.example.consumirapi.dominio.serviciosDominio.interfaces.ISdToken;
import com.example.consumirapi.persistencia.agenteServicios.ITokenAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SdToken implements ISdToken {

    @Autowired
    private ITokenAccessService tokenAccessService;
    @Override
    public String obtenerToken() {
        return this.tokenAccessService.obtenerToken();
    }
}
