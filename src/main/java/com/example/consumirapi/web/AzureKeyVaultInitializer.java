package com.example.consumirapi.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

public class AzureKeyVaultInitializer {

    private final String urlPool;

    private Properties azureProperties;

    public AzureKeyVaultInitializer(String urlPool) {
        this.urlPool = urlPool;
        azureProperties = new Properties();
    }

    public void init(){
        AzureKeyVaultService azureKeyVaultService = new AzureKeyVaultService(urlPool);
        String secretUrl = azureKeyVaultService.getSecret("spring-datasource-url");
        String secretUsername = azureKeyVaultService.getSecret("spring-datasource-username");
        String secretPassword = azureKeyVaultService.getSecret("spring-datasource-password");

        azureProperties.setProperty("spring.datasource.url", secretUrl);
        azureProperties.setProperty("spring.datasource.username", secretUsername);
        azureProperties.setProperty("spring.datasource.password", secretPassword);
    }

    public Properties getAzureProperties() {
        return azureProperties;
    }
}
