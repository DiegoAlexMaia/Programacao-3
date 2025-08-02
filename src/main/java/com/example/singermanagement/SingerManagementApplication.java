package com.example.singermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Sistema de Gerenciamento de Cantores
 * 
 * @author Sistema
 * @version 1.0.0
 */
@SpringBootApplication
public class SingerManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingerManagementApplication.class, args);
        
        System.out.println("\n" +
            "=====================================================\n" +
            "   Sistema de Gerenciamento de Cantores iniciado   \n" +
            "=====================================================\n" +
            "   Acesse: http://localhost:8080                   \n" +
            "   Documentação: /singers                          \n" +
            "   Login: /login                                   \n" +
            "                                                   \n" +
            "   Usuários de demonstração:                       \n" +
            "   - admin / 54321 (Administrador)                \n" +
            "   - diego / 12345 (Administrador)                \n" +
            "                                                   \n" +
            "   Tecnologias:                                    \n" +
            "   - Java 21                                      \n" +
            "   - Spring Boot 3.5.3                           \n" +
            "   - MySQL 8.1 (Docker)                          \n" +
            "   - Thymeleaf                                    \n" +
            "   - Spring Security                              \n" +
            "=====================================================\n"
        );
    }
}