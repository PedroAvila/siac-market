package com.example.consumirapi.dominio.contratosRepositorio;

import com.example.consumirapi.dominio.entidadesDominio.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsByNombre(String nombre);

}
