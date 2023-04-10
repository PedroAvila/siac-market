package com.example.consumirapi.dominio.serviciosDominio.interfaces;

import java.util.List;

public interface ISdBaseServicesApplication<T> {

     List<T> GetAllAsync();
    void CreateAsync(T entity);
    void UpdateAsync(T entity);

    void DeleteAsync(T entity);
}
