package com.forum.forumhub.impl;

import com.forum.forumhub.model.Curso;
import com.forum.forumhub.repository.CursoRepository;
import com.forum.forumhub.service.CursoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public Curso criarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    public List<Curso> buscarTodos() {
        return cursoRepository.findAll();
    }

    @Override
    public Optional<Curso> buscarPorNome(String nome) {
        return cursoRepository.findByNome(nome);
    }

    @Override
    public List<Curso> buscarPorNomeParcial(String fragmento) {
        return cursoRepository.findByNomeContainingIgnoreCase(fragmento);
    }

    @Override
    public void deletarCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    public Curso atualizarCurso(Long id, Curso cursoAtualizado) {
        return cursoRepository.findById(id)
                .map(curso -> {
                    curso.setNome(cursoAtualizado.getNome());
                    curso.setDescricao(cursoAtualizado.getDescricao());
                    return cursoRepository.save(curso);
                }).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }
}
