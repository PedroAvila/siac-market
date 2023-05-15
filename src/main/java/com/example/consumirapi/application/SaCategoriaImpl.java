package com.example.consumirapi.application;

import com.example.consumirapi.application.services.application.interfaces.SaCategoria;
import com.example.consumirapi.application.services.application.interfaces.dtos.CategoriaDto;
import com.example.consumirapi.cross.cutting.infrastructure.operations.exceptions.custome.response.ResponseData;
import com.example.consumirapi.cross.cutting.infrastructure.operations.exceptions.custome.response.ResponseService;
import com.example.consumirapi.dominio.services.domain.interfaces.SdCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SaCategoriaImpl implements SaCategoria {

    @Autowired
    private SdCategoria sdCategoria;

    @Autowired
    private GenericModelMapper modelMapper;

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<ResponseData<List<CategoriaDto>>> getAllAsync() {
        return sdCategoria.getAllAsync()
                .thenApply(categorias -> modelMapper.mapToListCategoriaDto(categorias))
                .thenCompose(categoriasDto -> ResponseService.createCompletableFutureResponse("Success", HttpStatus.OK, categoriasDto));
    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<ResponseData<CategoriaDto>> singleAsync(Long id) {
        return sdCategoria.singleAsync(id)
                .thenApply(categoria -> modelMapper.mapToCategoriaDto(categoria))
                .thenCompose(categoriaDto -> ResponseService.createCompletableFutureResponse("Success", HttpStatus.OK, categoriaDto));
    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<ResponseData<Void>> createAsync(CategoriaDto categoriaDto) {
        var categoria = modelMapper.mapToCategoria(categoriaDto);
        return sdCategoria.createAsync(categoria)
                .thenApply(category -> {
                    var message = "Success";
                    var status = HttpStatus.CREATED;
                    var responseData = new ResponseData<Void>(message, status, null);
                    return responseData;
                });
    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<ResponseData<Void>> updateAsync(CategoriaDto categoriaDto) {
        var categoria = this.modelMapper.mapToCategoria(categoriaDto);
        return sdCategoria.updateAsync(categoria)
                .thenApply(category -> {
                    var message = "Success";
                    var status = HttpStatus.OK;
                    var responseData = new ResponseData<Void>(message, status, null);
                    return responseData;
                });

    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<ResponseData<Void>> deleteAsync(Long id) {
        return sdCategoria.deleteAsync(id)
                .thenCompose(response-> ResponseService.createCompletableFutureResponse("Success", HttpStatus.OK, null));
    }
}
