package com.example.consumirapi.web;

import com.example.consumirapi.cross.cutting.infrastructure.operations.exceptions.AsyncCustomUncaughtExceptionHandler;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/*
* CountDownLatch es una herramienta de sincronización que se puede utilizar para coordinar la ejecución de múltiples hilos.
* Cuando se crea un objeto CountDownLatch, se establece un número inicial que representa la cantidad de eventos
* Que deben ocurrir antes de que los hilos que están esperando puedan continuar su ejecución.
* */
@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {

    @Bean
    public CountDownLatch latch() {
        return new CountDownLatch(3);
    }

    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(6);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("AsynchThread-");
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncCustomUncaughtExceptionHandler();
    }

}
