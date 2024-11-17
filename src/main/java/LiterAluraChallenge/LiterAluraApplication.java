package LiterAluraChallenge;

import LiterAluraChallenge.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

    @Autowired
    private Principal principal; // Inyecta la clase Principal

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args); // Inicia la aplicación Spring Boot
    }

    @Override
    public void run(String... args) throws Exception {
        principal.muestraElMenu(); // Muestra el menú cuando la aplicación arranca
    }
}
