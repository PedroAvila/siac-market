package com.example.consumirapi.web;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import org.springframework.stereotype.Component;
@Component
public class KeyVaultSecretManager {

    public String getSecretValue(String secretName) {
        SecretClient client = new SecretClientBuilder()
                .vaultUrl("https://tecsoftware.vault.azure.net/")
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();

        KeyVaultSecret secret = client.getSecret(secretName);
        return secret.getValue();
    }
}

