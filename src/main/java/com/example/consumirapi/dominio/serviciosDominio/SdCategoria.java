package com.example.consumirapi.dominio.serviciosDominio;

import com.example.consumirapi.dominio.contratosRepositorio.ICategoriaRepository;
import com.example.consumirapi.dominio.entidadesDominio.Categoria;
import com.example.consumirapi.dominio.serviciosDominio.interfaces.ISdCategoria;
import com.example.consumirapi.infraestructuraTransversal.operaciones.exceptions.FieldAlreadyExistException;
import com.example.consumirapi.infraestructuraTransversal.operaciones.exceptions.NotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SdCategoria implements ISdCategoria {

    private static final Logger log = LoggerFactory.getLogger(SdCategoria.class);

    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> GetAllAsync() {

        log.info("Inicio método GetAllAsync - obtener categorías");
        try {
            return this.categoriaRepository.findAll();
        } catch (Exception e) {
            log.error("Error al consultar categorías: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void CreateAsync(Categoria entity) {

        log.info("Inicio método CreateAsync - crear categoría");
        try {
            boolean exist = this.categoriaRepository.existsByNombre(entity.getNombre());
            if(!exist){
                categoriaRepository.save(entity);
            } else {
                log.info("La categoria ya existe");
                throw new FieldAlreadyExistException("La categoría ya existe");
            }
        } catch (Exception e){
            log.error("Error al registrar categoría " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void UpdateAsync(Categoria entity) {

        log.info("Inicio método UpdateAsync - actualizar categoría");
        try {
            Optional<Categoria> categoriaOptional = this.categoriaRepository.findById(entity.getId());
            if(categoriaOptional.isPresent()){
                Categoria categoria = categoriaOptional.get();
                categoria.setNombre(entity.getNombre());
                categoria.setEstado(entity.getEstado());

                this.categoriaRepository.save(categoria);
                this.categoriaRepository.flush();
            } else {
                log.info("Error al actualizar categoría");
                throw new NotFoundException("Error al actualizar categoría");
            }
        } catch (Exception e){
            log.error("Error al actualizar categoría " + e.getMessage());
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public void DeleteAsync(Categoria entity) {

    }
}
