package com.example.consumirapi.cross.cutting.infrastructure.operations.exceptions.custome.response;

import org.springframework.http.HttpStatus;

import java.util.concurrent.CompletableFuture;

public class ResponseService {
    public static <T> CompletableFuture<ResponseData<T>> createCompletableFutureResponse(String message, HttpStatus status, T data){
        ResponseData<T> responseData = new ResponseData<>(message, status, data);
        return CompletableFuture.completedFuture(responseData);
    }
}
