package com.example.consumirapi.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AzureKeyVaultInitializer {

    @Value("${spring-datasource-url}")
    private String springDatasourceUrl;

    @Value("${spring-datasource-username}")
    private String springDatasourceUsername;

    @Value("${spring-datasource-password}")
    private String springDatasourcePassword;

    @PostConstruct
    public void init(){
        AzureKeyVaultService azureKeyVaultService = new AzureKeyVaultService("https://tecsoftware.vault.azure.net/");
        String secretUrl = azureKeyVaultService.getSecret("spring-datasource-url");
        String secretUsername = azureKeyVaultService.getSecret("spring-datasource-username");
        String secretPassword = azureKeyVaultService.getSecret("spring-datasource-password");

        // Use los valores de los secretos para configurar la fuente de datos de Spring
        springDatasourceUrl = secretUrl;
        springDatasourceUsername = secretUsername;
        springDatasourcePassword = secretPassword;
    }
}
