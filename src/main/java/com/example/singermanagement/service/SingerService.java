package com.example.singermanagement.service;

import com.example.singermanagement.model.Singer;
import com.example.singermanagement.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Serviço para operações de negócio com cantores
 */
@Service
@Transactional
public class SingerService {

    @Autowired
    private SingerRepository singerRepository;

    /**
     * Lista todos os cantores ordenados por nome
     * @return lista de cantores
     */
    @Transactional(readOnly = true)
    public List<Singer> findAllSingers() {
        return singerRepository.findAllByOrderByNameAsc();
    }

    /**
     * Busca um cantor pelo ID
     * @param id ID do cantor
     * @return Optional contendo o cantor se encontrado
     */
    @Transactional(readOnly = true)
    public Optional<Singer> findSingerById(Long id) {
        return singerRepository.findById(id);
    }

    /**
     * Salva um novo cantor ou atualiza um existente
     * @param singer cantor a ser salvo
     * @return cantor salvo
     */
    public Singer saveSinger(Singer singer) {
        validateSinger(singer);
        return singerRepository.save(singer);
    }

    /**
     * Atualiza um cantor existente
     * @param id ID do cantor
     * @param updatedSinger dados atualizados do cantor
     * @return cantor atualizado
     * @throws RuntimeException se o cantor não for encontrado
     */
    public Singer updateSinger(Long id, Singer updatedSinger) {
        Optional<Singer> existingSinger = singerRepository.findById(id);
        
        if (existingSinger.isPresent()) {
            Singer singer = existingSinger.get();
            singer.setName(updatedSinger.getName());
            singer.setGenre(updatedSinger.getGenre());
            singer.setBirthDate(updatedSinger.getBirthDate());
            singer.setNationality(updatedSinger.getNationality());
            
            validateSinger(singer);
            return singerRepository.save(singer);
        } else {
            throw new RuntimeException("Cantor não encontrado com ID: " + id);
        }
    }

    /**
     * Remove um cantor pelo ID
     * @param id ID do cantor a ser removido
     * @throws RuntimeException se o cantor não for encontrado
     */
    public void deleteSinger(Long id) {
        if (singerRepository.existsById(id)) {
            singerRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cantor não encontrado com ID: " + id);
        }
    }

    /**
     * Busca cantores por nome (busca parcial)
     * @param name nome ou parte do nome
     * @return lista de cantores encontrados
     */
    @Transactional(readOnly = true)
    public List<Singer> findSingersByName(String name) {
        return singerRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Busca cantores por gênero musical
     * @param genre gênero musical
     * @return lista de cantores do gênero
     */
    @Transactional(readOnly = true)
    public List<Singer> findSingersByGenre(String genre) {
        return singerRepository.findByGenreIgnoreCase(genre);
    }

    /**
     * Busca cantores por nacionalidade
     * @param nationality nacionalidade
     * @return lista de cantores da nacionalidade
     */
    @Transactional(readOnly = true)
    public List<Singer> findSingersByNationality(String nationality) {
        return singerRepository.findByNationalityIgnoreCase(nationality);
    }

    /**
     * Conta o total de cantores
     * @return número total de cantores
     */
    @Transactional(readOnly = true)
    public long countSingers() {
        return singerRepository.count();
    }

    /**
     * Verifica se existe um cantor com o ID especificado
     * @param id ID do cantor
     * @return true se existe, false caso contrário
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return singerRepository.existsById(id);
    }

    /**
     * Valida os dados de um cantor
     * @param singer cantor a ser validado
     * @throws RuntimeException se os dados forem inválidos
     */
    private void validateSinger(Singer singer) {
        if (singer.getBirthDate() != null && singer.getBirthDate().isAfter(LocalDate.now())) {
            throw new RuntimeException("A data de nascimento não pode ser uma data futura");
        }
        
        // Outras validações de negócio podem ser adicionadas aqui
    }
}