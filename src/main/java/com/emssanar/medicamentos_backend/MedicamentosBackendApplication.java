package com.emssanar.medicamentos_backend;

// Importaciones necesarias para la configuración del proyecto Spring Boot
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Clase principal de la aplicación Spring Boot.
 * Configura y lanza la aplicación backend para gestionar medicamentos.
 */
@SpringBootApplication // Anotación principal que configura Spring Boot
@EntityScan("com.emssanar.medicamentos_backend.model") 
// Indica el paquete donde se encuentran las entidades JPA, como `Medicamento`.
@EnableJpaRepositories("com.emssanar.medicamentos_backend.repository")
// Indica el paquete donde se encuentran los repositorios JPA.
public class MedicamentosBackendApplication {

    /**
     * Método principal que lanza la aplicación.
     * @param args Argumentos pasados al ejecutar la aplicación.
     */
    public static void main(String[] args) {
        // Ejecuta la aplicación utilizando la configuración proporcionada por Spring Boot.
        SpringApplication.run(MedicamentosBackendApplication.class, args);
    }
}
