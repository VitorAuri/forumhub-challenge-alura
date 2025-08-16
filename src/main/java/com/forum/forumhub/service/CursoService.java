package com.forum.forumhub.service;

import com.forum.forumhub.model.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    Curso criarCurso(Curso curso);
    Optional<Curso> buscarPorId(Long id);
    List<Curso> buscarTodos();
    Optional<Curso> buscarPorNome(String nome);
    List<Curso> buscarPorNomeParcial(String fragmento);
    void deletarCurso(Long id);
    Curso atualizarCurso(Long id, Curso cursoAtualizado);
}
