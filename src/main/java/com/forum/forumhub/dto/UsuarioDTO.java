package com.forum.forumhub.dto;

import com.forum.forumhub.model.Usuario;

public record UsuarioDTO(
        Long id,
        String nome,
        String email
) {
    public static UsuarioDTO fromEntity(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}

