package com.forum.forumhub.repository;

import com.forum.forumhub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    List<Topico> findByCursoNome(String cursoNome);

    List<Topico> findByAutorNome(String autorNome);

    List<Topico> findByTituloContainingIgnoreCase(String tituloFragment);
    List<Topico> findByEstado(String estado);

}

