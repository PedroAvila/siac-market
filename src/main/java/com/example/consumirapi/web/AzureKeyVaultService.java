package com.example.consumirapi.web;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class AzureKeyVaultService {

    private final String keyVaultUrl;

    private final SecretClient secretClient;

    public AzureKeyVaultService(String keyVaultUrl) {
        this.keyVaultUrl = keyVaultUrl;

        //Autenticación con credenciales predeterminadas de Azure
        this.secretClient = new SecretClientBuilder()
                .vaultUrl(keyVaultUrl)
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();
    }

    public String getSecret(String secretName) {
        return secretClient.getSecret(secretName).getValue();
    }

}
