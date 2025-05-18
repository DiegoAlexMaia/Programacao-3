package br.edu.ifpb.movies.repository;

import br.edu.ifpb.movies.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Integer> {
}
