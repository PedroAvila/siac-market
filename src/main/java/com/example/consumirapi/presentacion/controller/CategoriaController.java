package com.example.consumirapi.presentacion.controller;

import com.example.consumirapi.aplicacion.serviciosAplicacion.ISaCategoria;
import com.example.consumirapi.dominio.entidadesDominio.Categoria;
import com.example.consumirapi.presentacion.models.CategoriaDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoriaController {

    @Autowired
    private ISaCategoria saCategoria;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/obtenerCategorias")
    public ResponseEntity<List<CategoriaDto>> GetAllAsync() {
        var categorias = saCategoria.GetAllAsync();
        List<CategoriaDto> categoriasDto = new ArrayList<>();

        for(Categoria categoria : categorias){
            CategoriaDto categoriaDto = this.modelMapper.map(categoria, CategoriaDto.class);
            categoriasDto.add(categoriaDto);
        }
        return new ResponseEntity<>(categoriasDto, HttpStatus.OK);
    }

    @PostMapping("/createCategoria")
    public ResponseEntity<CategoriaDto> Create(@Valid @RequestBody CategoriaDto categoriaDto) {

        var categoria = this.modelMapper.map(categoriaDto, Categoria.class);
        saCategoria.CreateAsync(categoria);

        CategoriaDto createCategoriaDto = this.modelMapper.map(categoria, CategoriaDto.class);
        return new ResponseEntity<>(createCategoriaDto, HttpStatus.CREATED);
    }

    @GetMapping("/greeting")
    public ResponseEntity<String> greeting() {
        String message = "Hello, world!";
        return ResponseEntity.ok(message);
    }
}
