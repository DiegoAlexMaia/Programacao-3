package br.edu.ifpb.movies.controller;

import jakarta.validation.Valid;
import br.edu.ifpb.movies.model.Filme;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import br.edu.ifpb.movies.service.FilmeService;

@Controller
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    // Lista todos os filmes
    @GetMapping("/filmes")
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmeService.listarTodos());
        return "listagem";  // Nome do template (listagem.html)
    }

    // Exibe formulário para adicionar novo filme
    @GetMapping("/filmes/novo")
    public String exibirFormularioNovoFilme(Model model) {
        model.addAttribute("filme", new Filme());
        return "add_filme";  // Nome do template (add_filme.html)
    }

    // Processa o formulário de cadastro de filme
    @PostMapping("/filmes/novo")
    public String processarFormularioNovoFilme(@Valid @ModelAttribute("filme") Filme filme,
                                               BindingResult result,
                                               Model model) {
        if (result.hasErrors()) {
            return "add_filme";  // Volta ao formulário em caso de erro
        }
        filmeService.salvar(filme);
        return "redirect:/filmes";  // Redireciona para a lista após salvar
    }
}
