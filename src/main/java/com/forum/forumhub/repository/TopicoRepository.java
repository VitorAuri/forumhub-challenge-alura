package com.forum.forumhub.repository;

import com.forum.forumhub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Buscar tópicos por curso (por id ou pelo nome do curso)
    List<Topico> findByCursoId(Long cursoId);
    List<Topico> findByCursoNome(String cursoNome);

    // Buscar por autor (id ou nome)
    List<Topico> findByAutorId(Long autorId);
    List<Topico> findByAutorNome(String autorNome);

    // Buscas de texto e filtros
    List<Topico> findByTituloContainingIgnoreCase(String tituloFragment);
    List<Topico> findByEstado(String estado);

    // Contagem / existência
    long countByCursoId(Long cursoId);
    boolean existsByTitulo(String titulo);
}

