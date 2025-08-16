package com.forum.forumhub.repository;

import com.forum.forumhub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca por email (login)
    Optional<Usuario> findByEmail(String email);

    // Busca por nome exato e por nome parcial (para autocompletes / listagens)
    Optional<Usuario> findByNome(String nome);
    List<Usuario> findByNomeContainingIgnoreCase(String nomeFragment);

}


