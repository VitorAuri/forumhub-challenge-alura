package com.forum.forumhub.repository;

import com.forum.forumhub.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByNome(String nome);

    List<Curso> findByNomeContainingIgnoreCase(String nomeFragment);

    boolean existsByNome(String nome);
}


