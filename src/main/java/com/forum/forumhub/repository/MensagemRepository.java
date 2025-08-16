package com.forum.forumhub.repository;

import com.forum.forumhub.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

    // Mensagens de um tópico (ordenadas)
    List<Mensagem> findByTopicoIdOrderByDataCriacaoAsc(Long topicoId);
    List<Mensagem> findByTopicoId(Long topicoId);

    // Mensagens por usuário
    List<Mensagem> findByUsuarioIdOrderByDataCriacaoDesc(Long usuarioId);
    List<Mensagem> findByUsuarioId(Long usuarioId);

    // Busca de conteúdo e por período
    List<Mensagem> findByConteudoContainingIgnoreCase(String texto);
    List<Mensagem> findByDataCriacaoBetween(LocalDateTime inicio, LocalDateTime fim);

    // Contagem para estatísticas
    long countByTopicoId(Long topicoId);
}


