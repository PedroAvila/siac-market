package com.example.consumirapi.infraestructuraTransversal.operaciones.exceptions;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

public class AsyncCustomUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        System.out.println("Method Name::" + method.getName());
        System.out.println("Exception occurred::" + ex);
    }
}
