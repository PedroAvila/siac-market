package com.example.consumirapi.web.controller;

import com.example.consumirapi.dominio.services.domain.interfaces.SdCategoria;
import com.example.consumirapi.web.GenericModelMapper;
import com.example.consumirapi.web.models.CategoriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private SdCategoria sdCategoria;

    @Autowired
    private GenericModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<List<CategoriaDto>> getAll() {
        var futureCategorias = sdCategoria.getAllAsync();
        return futureCategorias.thenApply(categorias -> modelMapper.mapToListCategoriaDto(categorias));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<CategoriaDto> single(@PathVariable Long id) {
        var futureCategoria = this.sdCategoria.singleAsync(id);
        return futureCategoria.thenApply(categoria -> this.modelMapper.mapToCategoriaDto(categoria));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaDto create(@Valid @RequestBody CategoriaDto categoriaDto) {
        var categoria = this.modelMapper.mapToCategoria(categoriaDto);
        sdCategoria.createAsync(categoria).join();
        return this.modelMapper.mapToCategoriaDto(categoria);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoriaDto update(@Valid @RequestBody CategoriaDto categoriaDto) {
        var categoria = this.modelMapper.mapToCategoria(categoriaDto);
        this.sdCategoria.updateAsync(categoria).join();
        return this.modelMapper.mapToCategoriaDto(categoria);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.sdCategoria.deleteAsync(id).join();
    }

    @GetMapping("/greeting")
    public ResponseEntity<String> greeting() {
        String message = "Hello, world!";
        return ResponseEntity.ok(message);
    }
}
