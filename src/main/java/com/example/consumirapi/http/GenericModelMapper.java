package com.example.consumirapi.http;

import com.example.consumirapi.dominio.entidadesDominio.Categoria;
import com.example.consumirapi.http.models.CategoriaDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenericModelMapper {
    @Autowired
    private ModelMapper mapper;

    public Categoria mapToCategoria(CategoriaDto categoriaDto) {
        return mapper.map(categoriaDto, Categoria.class);
//                .addMappings(mapping -> mapping.using(toIntegerEstadoCategoria)
//                        .map(CategoriaDto::getEstado, Categoria::setEstado));
//        return mapper.map(categoriaDto, Categoria.class);
    }

    public CategoriaDto mapToCategoriaDto(Categoria categoria) {
        return mapper.map(categoria, CategoriaDto.class);
    }
    public List<CategoriaDto> mapToListCategoriaDto(List<Categoria> categorias) {

        return categorias.stream()
                .map(categoria -> mapper.map(categoria, CategoriaDto.class))
                .collect(Collectors.toList());
    }

//    Converter<Integer, EstadoCategoria> toIntegerEstadoCategoria = new AbstractConverter<Integer, EstadoCategoria>() {
//        @Override
//        protected EstadoCategoria convert(Integer integer) {
//            return integer == 1 ? EstadoCategoria.ACTIVO : EstadoCategoria.INACTIVO;
//        }
//    };
//
//    Converter<EstadoCategoria, Integer> toEstadoCategoriaInteger = new AbstractConverter<EstadoCategoria, Integer>() {
//        @Override
//        protected Integer convert(EstadoCategoria estadoCategoria) {
//            return estadoCategoria == EstadoCategoria.ACTIVO ? 1 : 0;
//        }
//    };
}
