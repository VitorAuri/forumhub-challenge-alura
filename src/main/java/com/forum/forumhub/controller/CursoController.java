package com.forum.forumhub.controller;

import com.forum.forumhub.model.Curso;
import com.forum.forumhub.service.CursoService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // Criar um novo curso
    @PostMapping
    @Transactional
    public Curso criarCurso(@RequestBody Curso curso) {
        return cursoService.criarCurso(curso);
    }

    // Buscar todos os cursos
    @GetMapping
    public List<Curso> getTodosCursos() {
        return cursoService.buscarTodos();
    }

    // Buscar curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoPorId(@PathVariable Long id) {
        return cursoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar curso por nome exato
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Curso> getCursoPorNome(@PathVariable String nome) {
        return cursoService.buscarPorNome(nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar cursos por fragmento do nome
    @GetMapping("/buscar")
    public List<Curso> buscarCursosPorNomeParcial(@RequestParam String fragmento) {
        return cursoService.buscarPorNomeParcial(fragmento);
    }

    // Atualizar curso
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Curso> atualizarCurso(@PathVariable Long id, @RequestBody Curso cursoAtualizado) {
        try {
            Curso atualizado = cursoService.atualizarCurso(id, cursoAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar curso
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarCurso(@PathVariable Long id) {
        cursoService.deletarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
