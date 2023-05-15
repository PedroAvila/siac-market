package com.example.consumirapi.application.services.application.interfaces.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoriaDto {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @NotEmpty(message = "El nombre no puede estar vacío")
    @NotNull
    private String nombre;

    private boolean estado = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
