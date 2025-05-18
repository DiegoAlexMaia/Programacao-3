package br.edu.ifpb.movies.service;

import br.edu.ifpb.movies.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ifpb.movies.repository.FilmeRepository;

import java.util.List;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    @Autowired
    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    public Filme salvar(Filme filme) {
        return filmeRepository.save(filme);
    }

}
