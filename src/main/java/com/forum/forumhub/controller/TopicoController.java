package com.forum.forumhub.controller;

import com.forum.forumhub.model.Mensagem;
import com.forum.forumhub.model.Topico;
import com.forum.forumhub.service.MensagemService;
import com.forum.forumhub.service.TopicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;
    private final MensagemService mensagemService;

    public TopicoController(TopicoService topicoService, MensagemService mensagemService) {
        this.topicoService = topicoService;
        this.mensagemService = mensagemService;
    }

    @GetMapping
    public ResponseEntity<List<Topico>> getTodosTopicos() {
        return ResponseEntity.ok(topicoService.getTodosTopicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> getTopicoPorId(@PathVariable Long id) {
        return topicoService.getTopicoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<Topico> criarTopico(@RequestBody Topico topico) {
        Topico criado = topicoService.criarTopico(topico);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Topico> editarTopico(@PathVariable Long id, @RequestBody Topico topicoAtualizado) {
        return topicoService.editarTopico(id, topicoAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarTopico(@PathVariable Long id) {
        if (topicoService.getTopicoPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        topicoService.deletarTopico(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/mensagens")
    public ResponseEntity<List<Mensagem>> getMensagens(@PathVariable Long id) {
        Optional<Topico> topico = topicoService.getTopicoPorId(id);
        if (topico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Mensagem> mensagens = mensagemService.getMensagensPorTopico(id);
        return ResponseEntity.ok(mensagens);
    }


    @PostMapping("/{id}/mensagens")
    @Transactional
    public ResponseEntity<Mensagem> criarMensagemNoTopico(
            @PathVariable Long id,
            @RequestBody Mensagem mensagem) {

        Optional<Topico> topicoOpt = topicoService.getTopicoPorId(id);
        if (topicoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        mensagem.setTopico(topicoOpt.get());
        mensagem.setDataCriacao(java.time.LocalDateTime.now());

        if (mensagem.getUsuario() == null) {
            return ResponseEntity.badRequest().build();
        }

        Mensagem nova = mensagemService.criarMensagem(mensagem);
        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }
}
