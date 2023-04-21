package properties.cloud.vault;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("properties.cloud.vault")
@PropertySource("classpath:cloud.properties")
public class AzureKeyValueConfig {
}
