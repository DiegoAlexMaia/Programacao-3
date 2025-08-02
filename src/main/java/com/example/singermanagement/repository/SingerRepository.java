package com.example.singermanagement.repository;

import com.example.singermanagement.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositório para operações com a entidade Singer
 */
@Repository
public interface SingerRepository extends JpaRepository<Singer, Long> {

    /**
     * Busca cantores por nome (busca parcial, case insensitive)
     * @param name nome ou parte do nome do cantor
     * @return lista de cantores encontrados
     */
    @Query("SELECT s FROM Singer s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Singer> findByNameContainingIgnoreCase(@Param("name") String name);

    /**
     * Busca cantores por gênero musical
     * @param genre gênero musical
     * @return lista de cantores do gênero especificado
     */
    List<Singer> findByGenreIgnoreCase(String genre);

    /**
     * Busca cantores por nacionalidade
     * @param nationality nacionalidade
     * @return lista de cantores da nacionalidade especificada
     */
    List<Singer> findByNationalityIgnoreCase(String nationality);

    /**
     * Busca cantores nascidos após uma data específica
     * @param date data de referência
     * @return lista de cantores nascidos após a data
     */
    List<Singer> findByBirthDateAfter(LocalDate date);

    /**
     * Busca cantores nascidos antes de uma data específica
     * @param date data de referência
     * @return lista de cantores nascidos antes da data
     */
    List<Singer> findByBirthDateBefore(LocalDate date);

    /**
     * Busca cantores nascidos entre duas datas
     * @param startDate data inicial
     * @param endDate data final
     * @return lista de cantores nascidos no período
     */
    List<Singer> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);

    /**
     * Lista todos os cantores ordenados por nome
     * @return lista de cantores ordenada por nome
     */
    List<Singer> findAllByOrderByNameAsc();

    /**
     * Lista todos os cantores ordenados por data de nascimento (mais novos primeiro)
     * @return lista de cantores ordenada por data de nascimento descendente
     */
    List<Singer> findAllByOrderByBirthDateDesc();
}