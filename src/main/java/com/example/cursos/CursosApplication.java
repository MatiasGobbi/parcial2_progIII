package com.example.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursosApplication {
    public static void main(String[] args) {
        // Inicia la aplicación Spring Boot y expone los endpoints
        SpringApplication.run(CursosApplication.class, args);
    }
}
