package com.example.consumirapi.cross.cutting.infrastructure.operations.exceptions.custome.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

public class ResponseData<T> {
    private String messge;
    private HttpStatus status;
    private T data;

    public ResponseData(String messge, HttpStatus status, T data) {
        this.messge = messge;
        this.status = status;
        this.data = data;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
