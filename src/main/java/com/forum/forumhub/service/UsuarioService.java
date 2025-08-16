package com.forum.forumhub.service;

import com.forum.forumhub.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario criarUsuario(Usuario usuario);

    Optional<Usuario> getUsuarioPorId(Long id);

    List<Usuario> getTodosUsuarios();

    Optional<Usuario> editarUsuario(Long id, Usuario usuarioAtualizado);

    void deletarUsuario(Long id);

    Optional<Usuario> buscarPorEmail(String email);

    Optional<Usuario> buscarPorNome(String nome);
}


