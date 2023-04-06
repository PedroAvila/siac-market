package com.example.consumirapi.dominio.contratosRepositorio;

import com.example.consumirapi.dominio.entidadesDominio.Categoria;

public interface ICategoriaRepository extends IBaseRepository<Categoria, Long> {

    boolean existsByNombre(String nombre);

}
