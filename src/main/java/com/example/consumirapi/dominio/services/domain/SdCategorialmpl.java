package com.example.consumirapi.dominio.services.domain;

import com.example.consumirapi.dominio.contracts.repository.CategoriaRepository;
import com.example.consumirapi.dominio.entities.domain.Categoria;
import com.example.consumirapi.dominio.services.domain.interfaces.SdCategoria;
import com.example.consumirapi.cross.cutting.infrastructure.operations.exceptions.FieldAlreadyExistException;
import com.example.consumirapi.cross.cutting.infrastructure.operations.exceptions.NotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

@Service
public class SdCategorialmpl implements SdCategoria {

    private static final Logger log = LoggerFactory.getLogger(SdCategorialmpl.class);

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CountDownLatch countDownLatch;

    @Override
    @Transactional(readOnly = true)
    @Async("asyncExecutor")
    public CompletableFuture<List<Categoria>> getAllAsync() {

        log.info("Inicio método GetAllAsync - obtener categorías");
        try {
            var categorias = this.categoriaRepository.findAll();
            this.countDownLatch.countDown();
            return CompletableFuture.completedFuture(categorias);
        } catch (Exception e) {
            String mensajeError = "Error al consultar categorías: " + e.getMessage();
            log.error(mensajeError);
            throw new ServiceException(mensajeError, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    @Async("asyncExecutor")
    public CompletableFuture<Categoria> singleAsync(Long id) {
        log.info("Inicio método singleAsync - buscar categoría por id");
        try {
            Optional<Categoria> categoria = this.categoriaRepository.findById(id);
            if (categoria.isPresent()){
                this.countDownLatch.countDown();
                return CompletableFuture.completedFuture(categoria.get());
            }
            else {
                log.error("Error al consultar categoría");
                throw new NotFoundException("Categoría no encontrada");
            }
        } catch (Exception e){
            String mensajeError = "Error al consultar categoría " + e.getMessage();
            log.error(mensajeError);
            throw new ServiceException(mensajeError, e);
        }
    }

    @Override
    @Transactional
    @Async("asyncExecutor")
    public CompletableFuture<Void> createAsync(Categoria entity) {

        log.info("Inicio método createAsync - crear categoría");
        try {
            boolean exist = this.categoriaRepository.existsByNombre(entity.getNombre());
            if(!exist){
                var future = CompletableFuture.runAsync(()-> categoriaRepository.save(entity));
                future.thenRun(()-> this.countDownLatch.countDown());
                return future;
            } else {
                log.warn("La categoria ya existe");
                throw new FieldAlreadyExistException("La categoría ya existe");
            }
        } catch (Exception e){
            String mensajeError = "Error al registrar categoría " + e.getMessage();
            log.error(mensajeError);
            throw new ServiceException(mensajeError, e);
        }
    }

    @Override
    @Transactional
    @Async("asyncExecutor")
    public CompletableFuture<Void> updateAsync(Categoria entity) {

        log.info("Inicio método updateAsync - actualizar categoría");
        try {
            Optional<Categoria> categoriaOptional = this.categoriaRepository.findById(entity.getId());
            if(categoriaOptional.isPresent()){
                Categoria categoria = categoriaOptional.get();
                categoria.setNombre(entity.getNombre());
                categoria.setEstado(entity.getEstado());

                var future = CompletableFuture.runAsync(()-> this.categoriaRepository.save(categoria));
                future.thenRun(()-> this.countDownLatch.countDown());
                return future;
            } else {
                log.error("Error al actualizar categoría");
                throw new NotFoundException("Error al actualizar categoría");
            }
        } catch (Exception e){
            log.error("Error al actualizar categoría " + e.getMessage());
            throw new ServiceException("Error al actualizar categoría", e);
        }
    }

    @Override
    @Transactional
    @Async("asyncExecutor")
    public CompletableFuture<Void> deleteAsync(Long id) {

        log.info("Inicio método deleteAsync - borrar categoría");
        try {
            var future = CompletableFuture.runAsync(()-> this.categoriaRepository.deleteById(id));
            future.thenRun(()-> this.countDownLatch.countDown());
            return future;
        }catch (Exception e){
            String mensajeError = "Error al eliminar categoría " + e.getMessage();
            log.error(mensajeError, e);
            throw new ServiceException(mensajeError, e);
        }
    }
}
