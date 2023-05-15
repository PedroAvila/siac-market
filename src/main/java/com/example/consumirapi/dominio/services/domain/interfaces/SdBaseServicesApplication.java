package com.example.consumirapi.dominio.services.domain.interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SdBaseServicesApplication<T> {

    CompletableFuture<List<T>> getAllAsync();
    CompletableFuture<T> singleAsync(Long id);
    CompletableFuture<Void> createAsync(T entity);
    CompletableFuture<Void> updateAsync(T entity);
    CompletableFuture<Void> deleteAsync(Long id);
}
