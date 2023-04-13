package com.example.consumirapi.dominio.serviciosDominio.interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface ISdBaseServicesApplication<T> {

     CompletableFuture<List<T>> getAllAsync();
    CompletableFuture<T> singleAsync(Long id);
    CompletableFuture<Void> createAsync(T entity);
    CompletableFuture<Void> updateAsync(T entity);
    CompletableFuture<Void> deleteAsync(Long id);
}
