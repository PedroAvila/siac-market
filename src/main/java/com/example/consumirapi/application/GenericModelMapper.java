package com.example.consumirapi.application;

import com.example.consumirapi.dominio.entities.domain.Categoria;
import com.example.consumirapi.dominio.entities.domain.SubCategoria;
import com.example.consumirapi.application.services.application.interfaces.dtos.CategoriaDto;
import com.example.consumirapi.application.services.application.interfaces.dtos.SubCategoriaDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenericModelMapper {
    @Autowired
    private ModelMapper mapper;

    /* Categorías */
    public Categoria mapToCategoria(CategoriaDto categoriaDto) {
        return mapper.map(categoriaDto, Categoria.class);
    }

    public CategoriaDto mapToCategoriaDto(Categoria categoria) {
        return mapper.map(categoria, CategoriaDto.class);
    }

    public List<CategoriaDto> mapToListCategoriaDto(List<Categoria> categorias) {
        return categorias.stream()
                .map(categoria -> mapper.map(categoria, CategoriaDto.class))
                .collect(Collectors.toList());
    }

    /* SubCategorías */
    public List<SubCategoriaDto> mapToListSubCategoriaDto(List<SubCategoria> subCategorias){
        return subCategorias.stream()
                .map(subCategoria -> mapper.map(subCategoria, SubCategoriaDto.class))
                .collect(Collectors.toList());
    }

    public SubCategoria mapToSubCategoria(SubCategoriaDto subCategoriaDto) {
        return mapper.map(subCategoriaDto, SubCategoria.class);
    }

    public SubCategoriaDto mapToSubCategoriaDto(SubCategoria subCategoria){
        return mapper.map(subCategoria, SubCategoriaDto.class);
    }
}
