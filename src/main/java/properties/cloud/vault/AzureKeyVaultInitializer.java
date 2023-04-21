package properties.cloud.vault;

import java.util.Properties;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AzureKeyVaultInitializer {

    @Value("${spring.cloud.azure.keyvault.secret.endpoint}")
    private String azureKeyVaultEndpoint;

    private Properties azureProperties = new Properties();

    @PostConstruct
    public void init(){
        AzureKeyVaultService azureKeyVaultService = new AzureKeyVaultService(azureKeyVaultEndpoint);
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
