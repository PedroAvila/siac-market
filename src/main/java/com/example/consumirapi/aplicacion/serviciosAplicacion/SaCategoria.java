package com.example.consumirapi.aplicacion.serviciosAplicacion;

import com.example.consumirapi.dominio.entidadesDominio.Categoria;
import com.example.consumirapi.dominio.serviciosDominio.ISdCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaCategoria implements ISaCategoria {

    @Autowired
    private ISdCategoria sdCategoria;
    @Override
    public List<Categoria> GetAllAsync() {
        return sdCategoria.GetAllAsync();
    }

    @Override
    public void CreateAsync(Categoria entity) {
        sdCategoria.CreateAsync(entity);
    }

    @Override
    public void UpdateAsync(Categoria entity) {

    }

    @Override
    public void DeleteAsync(Categoria entity) {

    }
}
