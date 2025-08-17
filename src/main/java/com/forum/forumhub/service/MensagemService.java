package com.forum.forumhub.service;

import com.forum.forumhub.model.Mensagem;
import com.forum.forumhub.repository.MensagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensagemService {

    private final MensagemRepository mensagemRepository;

    public MensagemService(MensagemRepository mensagemRepository) {
        this.mensagemRepository = mensagemRepository;
    }

    public List<Mensagem> getMensagensPorTopico(Long topicoId) {
        return mensagemRepository.findByTopicoIdOrderByDataCriacaoAsc(topicoId);
    }

    public Mensagem criarMensagem(Mensagem mensagem) {
        return mensagemRepository.save(mensagem);
    }
}

