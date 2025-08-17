package com.forum.forumhub.repository;

import com.forum.forumhub.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

    List<Mensagem> findByTopicoIdOrderByDataCriacaoAsc(Long topicoId);


}


