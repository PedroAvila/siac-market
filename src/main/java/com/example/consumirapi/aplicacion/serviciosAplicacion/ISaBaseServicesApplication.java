package com.example.consumirapi.aplicacion.serviciosAplicacion;

import java.util.List;

public interface ISaBaseServicesApplication<T extends Object> {

    List<T> GetAllAsync();
    void CreateAsync(T entity);
    void UpdateAsync(T entity);

    void DeleteAsync(T entity);
}
