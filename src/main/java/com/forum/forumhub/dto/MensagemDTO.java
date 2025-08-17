package com.forum.forumhub.dto;

import com.forum.forumhub.model.Mensagem;

import java.time.LocalDateTime;

public record MensagemDTO(
        Long id,
        String conteudo,
        LocalDateTime dataCriacao,
        UsuarioDTO usuario
) {
    public static MensagemDTO fromEntity(Mensagem mensagem) {
        return new MensagemDTO(
                mensagem.getId(),
                mensagem.getConteudo(),
                mensagem.getDataCriacao(),
                UsuarioDTO.fromEntity(mensagem.getUsuario())
        );
    }
}
