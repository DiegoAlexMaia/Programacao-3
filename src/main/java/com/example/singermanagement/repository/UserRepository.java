package com.example.singermanagement.repository;

import com.example.singermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para operações com a entidade User
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca um usuário pelo nome de usuário
     * @param username nome de usuário
     * @return Optional contendo o usuário se encontrado
     */
    Optional<User> findByUsername(String username);

    /**
     * Busca um usuário pelo nome de usuário com o papel carregado
     * @param username nome de usuário
     * @return Optional contendo o usuário com papel carregado
     */
    @Query("SELECT u FROM User u JOIN FETCH u.role WHERE u.username = :username")
    Optional<User> findByUsernameWithRole(@Param("username") String username);

    /**
     * Verifica se existe um usuário com o nome especificado
     * @param username nome de usuário
     * @return true se existe, false caso contrário
     */
    boolean existsByUsername(String username);

    /**
     * Verifica se um usuário está habilitado
     * @param username nome de usuário
     * @return true se habilitado, false caso contrário
     */
    @Query("SELECT u.enabled FROM User u WHERE u.username = :username")
    Boolean isUserEnabled(@Param("username") String username);
}