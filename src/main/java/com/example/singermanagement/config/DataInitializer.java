package com.example.singermanagement.config;

import com.example.singermanagement.model.Role;
import com.example.singermanagement.model.User;
import com.example.singermanagement.repository.RoleRepository;
import com.example.singermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Inicializador de dados para criar usuários e papéis padrão
 * Só executa se os dados não existirem no banco
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se já existem dados no banco
        if (userRepository.count() == 0) {
            initializeData();
        }
    }

    private void initializeData() {
        // Criar papel ADMIN se não existir
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseGet(() -> {
                    Role role = new Role("ADMIN");
                    return roleRepository.save(role);
                });

        // Criar papel USER se não existir
        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> {
                    Role role = new Role("USER");
                    return roleRepository.save(role);
                });

        // Criar usuário admin se não existir
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("54321"));
            admin.setRole(adminRole);
            admin.setEnabled(true);
            userRepository.save(admin);
            
            System.out.println("✓ Usuário admin criado com sucesso!");
        }

        // Criar usuário diego se não existir
        if (!userRepository.existsByUsername("diego")) {
            User diego = new User();
            diego.setUsername("diego");
            diego.setPassword(passwordEncoder.encode("12345"));
            diego.setRole(adminRole);
            diego.setEnabled(true);
            userRepository.save(diego);
            
            System.out.println("✓ Usuário diego criado com sucesso!");
        }

        System.out.println("✓ Inicialização de dados concluída!");
    }
}