package com.forum.forumhub.repository;

import com.forum.forumhub.model.UsuarioCurso;
import com.forum.forumhub.model.UsuarioCursoId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioCursoRepository extends JpaRepository<UsuarioCurso, UsuarioCursoId> {

    // Relações por usuário ou por curso
    List<UsuarioCurso> findByUsuarioId(Long usuarioId);
    List<UsuarioCurso> findByCursoId(Long cursoId);

    // Verificar inscrição específica
    Optional<UsuarioCurso> findByUsuarioIdAndCursoId(Long usuarioId, Long cursoId);
    boolean existsByUsuarioIdAndCursoId(Long usuarioId, Long cursoId);
}

