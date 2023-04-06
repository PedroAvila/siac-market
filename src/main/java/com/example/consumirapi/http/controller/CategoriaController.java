package com.example.consumirapi.http.controller;

import com.example.consumirapi.dominio.entidadesDominio.Categoria;
import com.example.consumirapi.dominio.serviciosDominio.ISdCategoria;
import com.example.consumirapi.http.models.CategoriaDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CategoriaController {

    @Autowired
    private ISdCategoria sdCategoria;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/obtenerCategorias")
    public ResponseEntity<List<CategoriaDto>> GetAllAsync() {
        var categorias = sdCategoria.GetAllAsync();

        List<CategoriaDto> categoriasDto = categorias.stream()
                .map(categoria -> modelMapper.map(categoria, CategoriaDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(categoriasDto, HttpStatus.OK);
    }

    @PostMapping("/createCategoria")
    public ResponseEntity<CategoriaDto> Create(@Valid @RequestBody CategoriaDto categoriaDto) {

        var categoria = this.modelMapper.map(categoriaDto, Categoria.class);
        sdCategoria.CreateAsync(categoria);

        CategoriaDto createCategoriaDto = this.modelMapper.map(categoria, CategoriaDto.class);
        return new ResponseEntity<>(createCategoriaDto, HttpStatus.CREATED);
    }

    @GetMapping("/greeting")
    public ResponseEntity<String> greeting() {
        String message = "Hello, world!";
        return ResponseEntity.ok(message);
    }
}
