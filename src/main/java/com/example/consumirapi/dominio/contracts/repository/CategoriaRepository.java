package com.example.consumirapi.dominio.contracts.repository;

import com.example.consumirapi.dominio.entities.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsByNombre(String nombre);

}
