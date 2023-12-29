# Custom Refresh token implementation using Spring Boot and Azure PostgreSQL
## Project Documentation

Azure PostgreSQL provides three alternatives to Authenticate 
![image](https://github.com/mjalaf/azure-postgresql-java-spring-cloud/assets/13685129/d6fee7b2-42e3-4ff8-ae4b-e23799c62895)

When using Microsoft Entra (ex Azure AD) The following high-level diagram summarizes how authentication with Azure Database for PostgreSQL works. 

![image](https://github.com/mjalaf/azure-postgresql-java-spring-cloud/assets/13685129/43363ca2-a669-49d7-894c-dc38322eb018)

[Reference](https://learn.microsoft.com/en-us/azure/postgresql/single-server/concepts-azure-ad-authentication#architecture)

Azure Database for PosgressSQL Support configuered with Microsoft Entra suport
  - Domains Users
  - Groups
  - Service Principal
  - Managed Identity

When authenticated with a Sevice Principal (without using the passwordless flag), we have to get and Azure AD Token and this token needs to be refreshed automatically every our.

Based on this post: https://www.azureblue.io/how-to-authenicated-aad-identity-against-postgres-using-spring-boot/

I wrote to prototype project 
  - refreshtokenjpnsample
  - refreshtokensample

Both projects are implemented the simpletokencache to refresh the token
https://learn.microsoft.com/en-us/java/api/com.azure.core.credential.simpletokencache?view=azure-java-stable

Another Alternative is using the Passwordless feature)
[Connect to Azure PostgreSQL using a service principal]
(https://learn.microsoft.com/en-us/azure/postgresql/flexible-server/how-to-connect-with-managed-identity)


### Contributing

If you would like to contribute to this project, please follow the guidelines outlined in the [Contributing](/workspaces/azure-postgresql-java-spring-cloud/CONTRIBUTING.md) file.

### License

This project is licensed under the [MIT License](/workspaces/azure-postgresql-java-spring-cloud/LICENSE).
## 
