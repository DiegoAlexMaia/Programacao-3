package com.example.singermanagement.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador principal para navegação geral da aplicação
 */
@Controller
public class MainController {

    /**
     * Página inicial - redireciona para a lista de cantores
     */
    @GetMapping("/")
    public String home() {
        return "redirect:/singers";
    }

    /**
     * Página de login
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "logout", required = false) String logout,
                       Model model) {
        
        if (error != null) {
            model.addAttribute("errorMessage", "Usuário ou senha inválidos!");
        }
        
        if (logout != null) {
            model.addAttribute("successMessage", "Logout realizado com sucesso!");
        }
        
        return "login";
    }

    /**
     * Página de acesso negado
     */
    @GetMapping("/access-denied")
    public String accessDenied(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null) {
            model.addAttribute("username", auth.getName());
        }
        
        return "access_denied";
    }

    /**
     * Página sobre o sistema (opcional)
     */
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("appName", "Sistema de Gerenciamento de Cantores");
        model.addAttribute("version", "1.0.0");
        model.addAttribute("description", "Sistema para cadastro e gerenciamento de cantores com Spring Boot");
        return "about";
    }
}