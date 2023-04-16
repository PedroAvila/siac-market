package com.example.consumirapi.dominio.contracts.repository;

import com.example.consumirapi.dominio.entities.domain.SubCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Long> {

    boolean existsByNombre(String nombre);
}
