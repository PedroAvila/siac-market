package com.example.consumirapi;

import org.springframework.boot.WebApplicationType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import properties.cloud.vault.AzureKeyValueConfig;
import properties.cloud.vault.AzureKeyVaultInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

@SpringBootApplication
public class SiacMarketApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AzureKeyValueConfig.class);
		AzureKeyVaultInitializer azureKeyVaultInitializer = ctx.getBean(AzureKeyVaultInitializer.class);

		SpringApplication app = new SpringApplication(SiacMarketApplication.class);
		app.setDefaultProperties(azureKeyVaultInitializer.getAzureProperties());

		app.run(args);
	}
}
