package azure.postgres.sample.refreshtokensample;

import com.azure.identity.ClientSecretCredentialBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import com.zaxxer.hikari.HikariConfig;
import com.azure.identity.ClientSecretCredential;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RefreshtokensampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RefreshtokensampleApplication.class, args);
	
		//Creating the HikariConfig Object and setting the JDBC URL, Username and DriverClassName
		//The JDBC URL should be in the format jdbc:postgresql://<SERVER_NAME>.postgres.database.azure.com/<DATABASE_NAME>
		//The Username should be in the format Service Principal display name
		//The DriverClassName should be org.postgresql.Driver
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:postgresql://xxxx.postgres.database.azure.com/postgres");
		config.setUsername("<SERVICE_PRINCIPAL_NAME>");
		config.setDriverClassName("org.postgresql.Driver");


		//Creating the ClientSecretCredential Object by passing the ClientId, ClientSecret and TenantId of the Service Principal
		//The Service Principal should have the access to the Azure Database for PostgreSQL
		//This cline secret credential will be used to get the access token for the Azure Database for PostgreSQL
		ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
        .clientId("<CLIENT_ID>")
        .clientSecret("<CLIENT_SECRET>")
        .tenantId("<TENANT_ID>")
        .build();

		//Creating the AzureAdDataSource Object by passing the TokenCredential and HikariConfig
		AzureAdDataSource ds = new AzureAdDataSource(clientSecretCredential,config);

        try {
          //Establishing the Connection to PostgreSQL
			var con = ds.getConnection();

		  //SAMPLE QUERY to RETRIEVE DATA
		  final String SQL_QUERY = "select * from todos;";

		  //Creating the Statement and Executing the Query to the PostgreSQL
		  PreparedStatement pst = con.prepareStatement(SQL_QUERY); ResultSet rs = pst.executeQuery();

		  //Iterating the ResultSet and printing the values
		  while (rs.next()) {
			System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
		  }
        }
        catch (SQLException sqlex){
            System.out.println("error" + sqlex);
        }
		catch (Exception ex) {
			System.out.println("error" + ex);
		}
		finally {
			ds.close();
		}


	}

}
