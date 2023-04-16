package com.example.consumirapi.web.controller;

import com.example.consumirapi.dominio.services.domain.interfaces.SdSubCategoria;
import com.example.consumirapi.web.GenericModelMapper;
import com.example.consumirapi.web.models.SubCategoriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/subCategorias")
public class SubCategoriaController {
    @Autowired
    private SdSubCategoria sdSubCategoria;
    @Autowired
    private GenericModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<List<SubCategoriaDto>> getAll() {
        var futureSubCategorias = this.sdSubCategoria.getAllAsync();
        return futureSubCategorias.thenApply(subCategorias -> modelMapper.mapToListSubCategoriaDto(subCategorias));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<SubCategoriaDto> single(@PathVariable Long id) {
        var futureSubCategoria = this.sdSubCategoria.singleAsync(id);
        return futureSubCategoria.thenApply(subCategoria -> this.modelMapper.mapToSubCategoriaDto(subCategoria));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubCategoriaDto create(@Valid @RequestBody SubCategoriaDto subCategoriaDto) {
        var subCategoria = this.modelMapper.mapToSubCategoria(subCategoriaDto);
        this.sdSubCategoria.createAsync(subCategoria).join();
        return this.modelMapper.mapToSubCategoriaDto(subCategoria);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public SubCategoriaDto update(@Valid @RequestBody SubCategoriaDto subCategoriaDto) {
        var subCategoria = this.modelMapper.mapToSubCategoria(subCategoriaDto);
        this.sdSubCategoria.updateAsync(subCategoria).join();
        return this.modelMapper.mapToSubCategoriaDto(subCategoria);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.sdSubCategoria.deleteAsync(id).join();
    }
}
