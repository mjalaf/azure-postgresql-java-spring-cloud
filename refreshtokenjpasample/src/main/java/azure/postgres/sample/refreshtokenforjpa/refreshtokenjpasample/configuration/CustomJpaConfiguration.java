package azure.postgres.sample.refreshtokenforjpa.refreshtokenjpasample.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


import azure.postgres.sample.refreshtokenforjpa.refreshtokenjpasample.model.Todo;
import azure.postgres.sample.refreshtokenforjpa.refreshtokenjpasample.repository.TodoRepository;

@Configuration
@EnableJpaRepositories(
  basePackageClasses = TodoRepository.class,
  entityManagerFactoryRef = "todosEntityManagerFactory"
)
public class CustomJpaConfiguration {
    
    @Bean
    public LocalContainerEntityManagerFactoryBean todosEntityManagerFactory(
      @Qualifier("customDataSourceForAzureAD") DataSource dataSource,
      EntityManagerFactoryBuilder builder) {
        return builder
          .dataSource(dataSource)
          .packages(Todo.class)
          .build();
    }
}
