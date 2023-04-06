package com.example.consumirapi.dominio.contratosRepositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface IBaseRepository<T extends Serializable , ID extends Long> extends JpaRepository<T, ID> {

}
