package azure.postgres.sample.refreshtokenforjpa.refreshtokenjpasample.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import com.azure.core.credential.AccessToken;
import com.azure.core.credential.SimpleTokenCache;
import com.azure.core.credential.TokenCredential;
import com.azure.core.credential.TokenRequestContext;

public class CustomDataSourceProperties extends DataSourceProperties {

    private final SimpleTokenCache cache;
    
    public CustomDataSourceProperties(TokenCredential clientSecretCredential) {

         this.cache = new SimpleTokenCache(() -> clientSecretCredential.getToken(createRequestContext()));
    
      }
    

    @Override
    public String determinePassword() {
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
