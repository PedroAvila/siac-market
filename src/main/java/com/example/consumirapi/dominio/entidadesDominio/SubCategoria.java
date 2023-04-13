package com.example.consumirapi.dominio.entidadesDominio;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SubCategorias")
public class SubCategoria implements Serializable {

    private static final long serialVersionUID = 393436279554677034L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

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
