package com.example.singermanagement.controller;

import com.example.singermanagement.model.Singer;
import com.example.singermanagement.service.SingerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para operações com cantores
 */
@Controller
@RequestMapping("/singers")
public class SingerController {

    @Autowired
    private SingerService singerService;

    /**
     * Lista todos os cantores (página pública)
     */
    @GetMapping
    public String listSingers(Model model, 
                             @RequestParam(value = "search", required = false) String search,
                             @RequestParam(value = "message", required = false) String message,
                             @RequestParam(value = "error", required = false) String error) {
        
        List<Singer> singers;
        
        if (search != null && !search.trim().isEmpty()) {
            singers = singerService.findSingersByName(search.trim());
            model.addAttribute("search", search);
        } else {
            singers = singerService.findAllSingers();
        }
        
        model.addAttribute("singers", singers);
        model.addAttribute("totalSingers", singers.size());
        
        if (message != null) {
            model.addAttribute("successMessage", message);
        }
        
        if (error != null) {
            model.addAttribute("errorMessage", error);
        }
        
        return "singers";
    }

    /**
     * Exibe formulário para adicionar novo cantor (requer autenticação ADMIN)
     */
    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddForm(Model model) {
        model.addAttribute("singer", new Singer());
        model.addAttribute("pageTitle", "Adicionar Cantor");
        model.addAttribute("action", "add");
        return "add_singer";
    }

    /**
     * Processa o formulário de adição de cantor (requer autenticação ADMIN)
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addSinger(@Valid @ModelAttribute("singer") Singer singer, 
                           BindingResult bindingResult, 
                           Model model,
                           RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Adicionar Cantor");
            model.addAttribute("action", "add");
            return "add_singer";
        }
        
        try {
            singerService.saveSinger(singer);
            redirectAttributes.addFlashAttribute("successMessage", 
                "Cantor '" + singer.getName() + "' foi adicionado com sucesso!");
            return "redirect:/singers";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erro ao salvar cantor: " + e.getMessage());
            model.addAttribute("pageTitle", "Adicionar Cantor");
            model.addAttribute("action", "add");
            return "add_singer";
        }
    }

    /**
     * Exibe formulário para editar cantor (requer autenticação ADMIN)
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Singer> singer = singerService.findSingerById(id);
        
        if (singer.isPresent()) {
            model.addAttribute("singer", singer.get());
            model.addAttribute("pageTitle", "Editar Cantor");
            model.addAttribute("action", "edit");
            return "edit_singer";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Cantor não encontrado!");
            return "redirect:/singers";
        }
    }

    /**
     * Processa o formulário de edição de cantor (requer autenticação ADMIN)
     */
    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateSinger(@PathVariable("id") Long id,
                              @Valid @ModelAttribute("singer") Singer singer, 
                              BindingResult bindingResult, 
                              Model model,
                              RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Editar Cantor");
            model.addAttribute("action", "edit");
            return "edit_singer";
        }
        
        try {
            Singer updatedSinger = singerService.updateSinger(id, singer);
            redirectAttributes.addFlashAttribute("successMessage", 
                "Cantor '" + updatedSinger.getName() + "' foi atualizado com sucesso!");
            return "redirect:/singers";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erro ao atualizar cantor: " + e.getMessage());
            model.addAttribute("pageTitle", "Editar Cantor");
            model.addAttribute("action", "edit");
            return "edit_singer";
        }
    }

    /**
     * Remove um cantor (requer autenticação ADMIN)
     */
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSinger(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            Optional<Singer> singer = singerService.findSingerById(id);
            
            if (singer.isPresent()) {
                String singerName = singer.get().getName();
                singerService.deleteSinger(id);
                redirectAttributes.addFlashAttribute("successMessage", 
                    "Cantor '" + singerName + "' foi removido com sucesso!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Cantor não encontrado!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", 
                "Erro ao remover cantor: " + e.getMessage());
        }
        
        return "redirect:/singers";
    }

    /**
     * Endpoint para busca de cantores via AJAX (opcional)
     */
    @GetMapping("/search")
    @ResponseBody
    public List<Singer> searchSingers(@RequestParam("q") String query) {
        if (query != null && !query.trim().isEmpty()) {
            return singerService.findSingersByName(query.trim());
        }
        return singerService.findAllSingers();
    }
}