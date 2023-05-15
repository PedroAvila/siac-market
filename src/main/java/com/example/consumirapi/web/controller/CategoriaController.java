package com.example.consumirapi.web.controller;

import com.example.consumirapi.application.services.application.interfaces.SaCategoria;
import com.example.consumirapi.application.GenericModelMapper;
import com.example.consumirapi.application.services.application.interfaces.dtos.CategoriaDto;
import com.example.consumirapi.cross.cutting.infrastructure.operations.exceptions.custome.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private SaCategoria saCategoria;

    @Autowired
    private GenericModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<ResponseData<List<CategoriaDto>>> getAll() {
        return saCategoria.getAllAsync();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<ResponseData<CategoriaDto>> single(@PathVariable Long id) {
        return saCategoria.singleAsync(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<ResponseData<Void>> create(@Valid @RequestBody CategoriaDto categoriaDto) {
        return saCategoria.createAsync(categoriaDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<ResponseData<Void>> update(@Valid @RequestBody CategoriaDto categoriaDto) {
        return saCategoria.updateAsync(categoriaDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<ResponseData<Void>> delete(@PathVariable Long id) {
        return saCategoria.deleteAsync(id);
    }

    @GetMapping("/greeting")
    public ResponseEntity<String> greeting() {
        String message = "Hello, world!";
        return ResponseEntity.ok(message);
    }
}
