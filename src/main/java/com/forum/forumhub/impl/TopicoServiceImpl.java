package com.forum.forumhub.impl;

import com.forum.forumhub.model.Topico;
import com.forum.forumhub.repository.TopicoRepository;
import com.forum.forumhub.service.TopicoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoServiceImpl implements TopicoService {
    private final TopicoRepository topicoRepository;

    public TopicoServiceImpl(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    @Override
    public List<Topico> getTodosTopicos() {
        return topicoRepository.findAll();
    }

    @Override
    public Optional<Topico> getTopicoPorId(Long id) {
        return topicoRepository.findById(id);
    }

    @Override
    public Topico criarTopico(Topico topico) {
        return topicoRepository.save(topico);
    }

    @Override
    public Optional<Topico> editarTopico(Long id, Topico topicoAtualizado) {
        return topicoRepository.findById(id).map(topico -> {
            topico.setTitulo(topicoAtualizado.getTitulo());
            topico.setMensagem(topicoAtualizado.getMensagem());
            return topicoRepository.save(topico);
        });
    }

    @Override
    public void deletarTopico(Long id) {
        topicoRepository.deleteById(id);
    }

    @Override
    public List<Topico> buscarPorCursoNome(String cursoNome) {
        return List.of();
    }

    @Override
    public List<Topico> buscarPorAutorNome(String autorNome) {
        return List.of();
    }

    @Override
    public List<Topico> buscarPorTitulo(String tituloFragment) {
        return List.of();
    }

    @Override
    public List<Topico> buscarPorEstado(String estado) {
        return List.of();
    }
}
