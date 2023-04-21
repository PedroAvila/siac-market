package com.example.consumirapi;

import com.example.consumirapi.web.AzureKeyVaultInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

@SpringBootApplication
public class SiacMarketApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SiacMarketApplication.class, args);
		SpringApplication app = new SpringApplication(SiacMarketApplication.class);
		ConfigurableEnvironment env = app.run(args).getEnvironment();
		env.getPropertySources().stream().filter(ps-> ps.containsProperty("spring.cloud.azure.keyvault.secret.endpoint")).findFirst().ifPresent(ps->{
			AzureKeyVaultInitializer azureKeyVaultInitializer = new AzureKeyVaultInitializer(ps.getProperty("spring.cloud.azure.keyvault.secret.endpoint").toString());
			azureKeyVaultInitializer.init();
			env.getPropertySources().addFirst(new PropertiesPropertySource("azure", azureKeyVaultInitializer.getAzureProperties()));
		});

		app.run(args);
	}

}
