package com.example.consumirapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SiacMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiacMarketApplication.class, args);
	}
}
