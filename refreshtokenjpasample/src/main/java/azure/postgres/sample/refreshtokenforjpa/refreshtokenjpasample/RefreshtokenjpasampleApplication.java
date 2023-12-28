package azure.postgres.sample.refreshtokenforjpa.refreshtokenjpasample;

import java.security.Timestamp;
import java.sql.Date;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import azure.postgres.sample.refreshtokenforjpa.refreshtokenjpasample.repository.TodoRepository;


@SpringBootApplication
public class RefreshtokenjpasampleApplication {

	@Autowired
   	TodoRepository todoRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(RefreshtokenjpasampleApplication.class, args);

	}

	@Bean
    public CommandLineRunner demoCommandLineRunner() {
        return args -> {
            System.out.println("Write X to close");

			Scanner scanner = new Scanner(System.in);

			String input = "";
			while (!input.equals("X")) {
				System.out.println("Posgress query run at:" + java.time.LocalTime.now()  );
				try {
					todoRepo.findAll().forEach(todo -> { System.out.println("Records: " + todo.getId() + " - " + todo.getDescription()); });

				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
				}
				System.out.println("Enter 'X' or Enter to Process the data Again to close the application.");
				input = scanner.nextLine();
			}
				
        };

	}	
}
