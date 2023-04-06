package com.example.consumirapi.dominio.serviciosDominio;

import com.example.consumirapi.dominio.contratosRepositorio.ICategoriaRepository;
import com.example.consumirapi.dominio.entidadesDominio.Categoria;
import com.example.consumirapi.infraestructuraTransversal.operaciones.exceptions.FieldAlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/*
* En esta capa com.example.consumirapi.dominio.serviciosDominio
* La responsabilidad principal de la capa de ServiciosDominio de aplicación es manejar la lógica de negocio
* No debe estar contaminada don responsabilidades de otra capa.
*
* En esta implementación cuando ocurre un error, se lanza una excepción ServiceException que contiene el mensaje de error
* y la causa de la excepción. Además, se registra la excepción utilizando la librería de logging.
* */
@Service
public class SdCategoria implements ISdCategoria {

    private static final Logger Log = LoggerFactory.getLogger(SdCategoria.class);

    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> GetAllAsync() {

        Log.info("Inicio método GetAllAsync - obtener categorías");
        try {
            return this.categoriaRepository.findAll();
        } catch (Exception e) {
            Log.error("Error al consultar categorias: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void CreateAsync(Categoria entity) {

        Log.info("Inicio método CreateAsync - crear categoría");
        try {
            boolean exist = this.categoriaRepository.existsByNombre(entity.getNombre());
            if(!exist){
                categoriaRepository.save(entity);
            } else {
                Log.info("La categoria ya existe");
                throw new FieldAlreadyExistException("La categoria ya existe");
            }
        } catch (Exception e){
            Log.error("Error al registrar categoría" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void UpdateAsync(Categoria entity) {

    }

    @Override
    public void DeleteAsync(Categoria entity) {

    }
}
