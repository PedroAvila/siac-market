package com.example.consumirapi.infraestructuraTransversal.operaciones;

import java.time.LocalDateTime;

public class Dto {

    private int id;
    private String name;
    private LocalDateTime fecha;

    public Dto(int id, String name, LocalDateTime fecha) {
        this.id = id;
        this.name = name;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
