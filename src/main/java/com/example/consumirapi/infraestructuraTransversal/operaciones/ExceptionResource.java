package com.example.consumirapi.infraestructuraTransversal.operaciones;

import com.example.consumirapi.infraestructuraTransversal.operaciones.exceptions.FieldAlreadyExistException;
import com.example.consumirapi.infraestructuraTransversal.operaciones.exceptions.MalformedHeaderException;
import com.example.consumirapi.infraestructuraTransversal.operaciones.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/*
* Esta clase me serviría para hacer los test a los endpoint
* */
@RestController
@RequestMapping(ExceptionResource.EXCEPTIONS)
public class ExceptionResource {

    public static final String EXCEPTIONS = "/exceptions";
    public static final String MY_FILTER = "/my-filter";
    public static final String OUT_OF_TIME = "/out-of-time";
    public static final String ID = "/{id}";
    public static final String ERROR = "/error";

    @GetMapping(value = MY_FILTER)
    public String myFilter() { return "{\"state\":\"my-filter\"}"; }

    @GetMapping(value = OUT_OF_TIME)
    public String outOfTime() { return "{\"state\":\"off\"}"; }

    @GetMapping(value = ERROR + ID)
    public Dto doError(@RequestHeader String token, @PathVariable int id, @RequestParam String param){
        if(token.equals("kk")){
            throw new MalformedHeaderException("token: " + token);
        } else if (id == 0) {
            throw new NotFoundException("id: " + id);
        } else if (param.isEmpty()) {
            throw new FieldAlreadyExistException("param: " + param);
        } else if (param.equals("kk")) {
            throw new FieldAlreadyExistException("param: " + param);
        }

        return new Dto(666, "demo", LocalDateTime.now());
    }
}
