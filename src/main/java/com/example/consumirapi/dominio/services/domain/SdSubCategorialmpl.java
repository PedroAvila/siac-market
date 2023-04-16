package com.example.consumirapi.dominio.services.domain;

import com.example.consumirapi.cross.cutting.infrastructure.operations.exceptions.FieldAlreadyExistException;
import com.example.consumirapi.cross.cutting.infrastructure.operations.exceptions.FieldInvalidException;
import com.example.consumirapi.cross.cutting.infrastructure.operations.exceptions.NotFoundException;
import com.example.consumirapi.dominio.contracts.repository.CategoriaRepository;
import com.example.consumirapi.dominio.contracts.repository.SubCategoriaRepository;
import com.example.consumirapi.dominio.entities.domain.SubCategoria;
import com.example.consumirapi.dominio.services.domain.interfaces.SdSubCategoria;
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
public class SdSubCategorialmpl implements SdSubCategoria {

    private static final Logger log = LoggerFactory.getLogger(SdSubCategorialmpl.class);

    @Autowired
    private SubCategoriaRepository subCategoriaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CountDownLatch countDownLatch;

    @Override
    @Transactional(readOnly = true)
    @Async("asyncExecutor")
    public CompletableFuture<List<SubCategoria>> getAllAsync() {
        log.info("Inicio método getAllAsync - obtener subCategorías");
        try {
            var subCategorias = this.subCategoriaRepository.findAll();
            this.countDownLatch.countDown();
            return CompletableFuture.completedFuture(subCategorias);
        } catch (Exception e){
            String mensajeError = "Error al consultar subCategorías: " + e.getMessage();
            log.error(mensajeError);
            throw new ServiceException(mensajeError, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    @Async("asyncExecutor")
    public CompletableFuture<SubCategoria> singleAsync(Long id) {
        log.info("Inicio método singleAsync - buscar subCategoría por id");
        try {
            Optional<SubCategoria> subCategoria = this.subCategoriaRepository.findById(id);
            if (subCategoria.isPresent()){
                this.countDownLatch.countDown();
                return CompletableFuture.completedFuture(subCategoria.get());
            }else {
                log.error("Error al consultar subCategoría");
                throw new NotFoundException("subCategoría no encontrada");
            }
        }catch (Exception e){
            String mensajeError = "Error al consultar subCategoría " + e.getMessage();
            log.error(mensajeError);
            throw new ServiceException(mensajeError, e);
        }
    }

    @Override
    @Transactional
    @Async("asyncExecutor")
    public CompletableFuture<Void> createAsync(SubCategoria entity) {
        log.info("Inicio método createAsync - crear subCategoría");
        try {
            boolean existCategoria = this.categoriaRepository.existsById(entity.getCategoria().getId());
            if (!existCategoria) {
                String mensaje = "La categoría no existe";
                log.error(mensaje);
                throw new FieldInvalidException(mensaje);
            }
            boolean exist = this.subCategoriaRepository.existsByNombre(entity.getNombre());
            if (!exist){
                var future = CompletableFuture.runAsync(()->subCategoriaRepository.save(entity));
                future.thenRun(()-> this.countDownLatch.countDown());
                return future;
            }else {
                String mensaje = "La subCategoría ya existe";
                log.warn(mensaje);
                throw new FieldAlreadyExistException(mensaje);
            }
        }catch (Exception e){
            String mensajeError = "Error al registrar subCategoría, " + e.getMessage();
            log.error(mensajeError);
            throw new ServiceException(mensajeError, e);
        }
    }

    @Override
    @Transactional
    @Async("asyncExecutor")
    public CompletableFuture<Void> updateAsync(SubCategoria entity) {
        log.info("Inicio método updateAsync - actualizar subCategoría");
        try {
            Optional<SubCategoria> subCategoriaOptional = this.subCategoriaRepository.findById(entity.getId());
            if (subCategoriaOptional.isPresent()){
                SubCategoria subCategoria = subCategoriaOptional.get();
                subCategoria.setCategoria(entity.getCategoria());
                subCategoria.setNombre(entity.getNombre());
                subCategoria.setEstado(entity.isEstado());

                var future = CompletableFuture.runAsync(()-> this.subCategoriaRepository.save(subCategoria));
                future.thenRun(()-> this.countDownLatch.countDown());
                return future;
            } else {
                log.error("Error al actualizar subCategoría");
                throw new NotFoundException("Error al actualizar subCategoría");
            }
        } catch (Exception e){
            log.error("Error al actualizar subCategoría " + e.getMessage());
            throw new ServiceException("Error al actualizar subCategoría", e);
        }
    }

    @Override
    @Transactional
    @Async("asyncExecutor")
    public CompletableFuture<Void> deleteAsync(Long id) {
        log.info("Inicio método deleteAsync - borrar subCategoría");
        try {
            var future = CompletableFuture.runAsync(()-> this.subCategoriaRepository.deleteById(id));
            future.thenRun(()-> this.countDownLatch.countDown());
            return future;
        } catch (Exception e){
            String mensajeError = "Error al eliminar subCategoría, " + e.getMessage();
            log.error(mensajeError);
            throw new ServiceException(mensajeError, e);
        }
    }
}
