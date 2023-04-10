package com.example.consumirapi.http.controller;

import com.example.consumirapi.dominio.serviciosDominio.interfaces.ISdCategoria;
import com.example.consumirapi.http.GenericModelMapper;
import com.example.consumirapi.http.models.CategoriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private ISdCategoria sdCategoria;

    @Autowired
    private GenericModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> GetAllAsync() {
        var categorias = sdCategoria.GetAllAsync();

        List<CategoriaDto> categoriasDto = modelMapper.mapToListCategoriaDto(categorias);

        return new ResponseEntity<>(categoriasDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> Create(@Valid @RequestBody CategoriaDto categoriaDto) {

        var categoria = this.modelMapper.mapToCategoria(categoriaDto);
        sdCategoria.CreateAsync(categoria);

        CategoriaDto createCategoriaDto = this.modelMapper.mapToCategoriaDto(categoria);
        return new ResponseEntity<>(createCategoriaDto, HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoriaDto Update(@Valid @RequestBody CategoriaDto categoriaDto){

        var categoria = this.modelMapper.mapToCategoria(categoriaDto);
        this.sdCategoria.UpdateAsync(categoria);

        CategoriaDto updateCategoriaDto = this.modelMapper.mapToCategoriaDto(categoria);
        return updateCategoriaDto;
    }

    @GetMapping("/greeting")
    public ResponseEntity<String> greeting() {
        String message = "Hello, world!";
        return ResponseEntity.ok(message);
    }
}
