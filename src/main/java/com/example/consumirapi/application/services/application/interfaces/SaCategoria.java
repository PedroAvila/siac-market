package com.example.consumirapi.application.services.application.interfaces;

import com.example.consumirapi.application.services.application.interfaces.dtos.CategoriaDto;
import com.example.consumirapi.cross.cutting.infrastructure.operations.exceptions.custome.response.ResponseData;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SaCategoria {
    CompletableFuture<ResponseData<List<CategoriaDto>>> getAllAsync();
    CompletableFuture<ResponseData<CategoriaDto>> singleAsync(Long id);
    CompletableFuture<ResponseData<Void>> createAsync(CategoriaDto entity);
    public CompletableFuture<ResponseData<Void>> updateAsync(CategoriaDto entity);
    public CompletableFuture<ResponseData<Void>> deleteAsync(Long id);
}
