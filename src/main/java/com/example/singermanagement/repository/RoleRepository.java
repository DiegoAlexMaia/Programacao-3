package com.example.singermanagement.repository;

import com.example.singermanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para operações com a entidade Role
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Busca um papel pelo nome
     * @param name nome do papel
     * @return Optional contendo o papel se encontrado
     */
    Optional<Role> findByName(String name);

    /**
     * Verifica se existe um papel com o nome especificado
     * @param name nome do papel
     * @return true se existe, false caso contrário
     */
    boolean existsByName(String name);
}