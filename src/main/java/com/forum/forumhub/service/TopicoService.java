package com.forum.forumhub.service;

import com.forum.forumhub.model.Topico;

import java.util.List;
import java.util.Optional;

public interface TopicoService {
    List<Topico> getTodosTopicos();
    Optional<Topico> getTopicoPorId(Long id);
    Topico criarTopico(Topico topico);
    Optional<Topico> editarTopico(Long id, Topico topicoAtualizado);
    void deletarTopico(Long id);

    // Consultas extras que podem ser úteis
    List<Topico> buscarPorCursoNome(String cursoNome);
    List<Topico> buscarPorAutorNome(String autorNome);
    List<Topico> buscarPorTitulo(String tituloFragment);
    List<Topico> buscarPorEstado(String estado);
}

