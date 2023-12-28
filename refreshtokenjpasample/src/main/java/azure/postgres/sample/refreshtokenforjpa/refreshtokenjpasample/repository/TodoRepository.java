package azure.postgres.sample.refreshtokenforjpa.refreshtokenjpasample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import azure.postgres.sample.refreshtokenforjpa.refreshtokenjpasample.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> 
{
    
}
