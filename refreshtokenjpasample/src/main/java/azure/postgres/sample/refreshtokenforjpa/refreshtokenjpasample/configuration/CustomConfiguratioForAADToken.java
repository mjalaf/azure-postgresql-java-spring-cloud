package azure.postgres.sample.refreshtokenforjpa.refreshtokenjpasample.configuration;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;

@Configuration
public class CustomConfiguratioForAADToken     {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public CustomDataSourceProperties dataSourceProperties() {

      // NOTE: Service principal information can be passed by parameter.  
      	ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
        .clientId("<CLIENT_ID>")
        .clientSecret("<CLIENT_SECRET>")
        .tenantId("<TENANT_ID>")
        .build();
        
        return new CustomDataSourceProperties(clientSecretCredential);
    }
    
    @Bean
    @Primary
    public DataSource customDataSourceForAzureAD() {
      return dataSourceProperties()
        .initializeDataSourceBuilder()
        .build();
  }
}
