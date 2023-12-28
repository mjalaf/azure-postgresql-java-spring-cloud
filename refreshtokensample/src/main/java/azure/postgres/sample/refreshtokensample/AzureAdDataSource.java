package azure.postgres.sample.refreshtokensample;

import com.azure.core.credential.AccessToken;
import com.azure.core.credential.SimpleTokenCache;
import com.azure.core.credential.TokenCredential;
import com.azure.core.credential.TokenRequestContext;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


//Creating the AzureAdDataSource class by extending the HikariDataSource
//This class will be used to get the access token from the TokenCredential and set it as the password to the HikariDataSource
//The HikariDataSource will be used to establish the connection to the Azure Database for PostgreSQL
public class AzureAdDataSource extends HikariDataSource {

    private final SimpleTokenCache cache;

    public AzureAdDataSource(TokenCredential aadTokenCredential,HikariConfig configuration) {
  
        this.cache = new SimpleTokenCache(() -> aadTokenCredential.getToken(createRequestContext()));
        super.setJdbcUrl(configuration.getJdbcUrl());
        super.setUsername(configuration.getUsername());
        super.setDriverClassName(configuration.getDriverClassName());

    }

    //Overriding the getPassword method to return the access token
    //This access token will be cached as the password to the HikariDataSource
    //The HikariDataSource will use this password to establish the connection to the Azure Database for PostgreSQL
    @Override
    public String getPassword() {
        final AccessToken accessToken = cache.getToken()
                                             .retry(1L)
                                             .blockOptional()
                                             .orElseThrow(() -> new RuntimeException("Attempt to retrieve AAD token failed"));
      
      return accessToken.getToken();
    }

    //Creating the TokenRequestContext Object by adding the scopes
    private static TokenRequestContext createRequestContext() {
        return new TokenRequestContext().addScopes("https://ossrdbms-aad.database.windows.net/.default");
    }
}