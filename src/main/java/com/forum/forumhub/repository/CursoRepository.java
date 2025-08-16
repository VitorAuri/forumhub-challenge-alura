package com.forum.forumhub.repository;

import com.forum.forumhub.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Buscar curso por nome (útil para criar tópicos referenciando curso)
    Optional<Curso> findByNome(String nome);

    // Busca parcial
    List<Curso> findByNomeContainingIgnoreCase(String nomeFragment);

    // Checar existência
    boolean existsByNome(String nome);
}


