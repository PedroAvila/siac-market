package com.example.consumirapi.presentacion;

import com.example.consumirapi.dominio.entidadesDominio.Categoria;
import com.example.consumirapi.presentacion.models.CategoriaDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();

        //Configuración de mapeo de clases
        modelMapper.createTypeMap(Categoria.class, CategoriaDto.class);

        return modelMapper;
    }
}
