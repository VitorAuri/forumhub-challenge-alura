package com.forum.forumhub.controller;

import com.forum.forumhub.dto.MensagemDTO;
import com.forum.forumhub.model.Mensagem;
import com.forum.forumhub.model.Topico;
import com.forum.forumhub.model.Usuario;
import com.forum.forumhub.model.UsuarioPrincipal;
import com.forum.forumhub.service.MensagemService;
import com.forum.forumhub.service.TopicoService;
import com.forum.forumhub.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final TopicoService topicoService;
    private final MensagemService mensagemService;


    public UsuarioController(UsuarioService usuarioService,
                             TopicoService topicoService,
                             MensagemService mensagemService) {
        this.usuarioService = usuarioService;
        this.topicoService = topicoService;
        this.mensagemService = mensagemService;
    }

    @GetMapping
    public List<Usuario> getTodosUsuarios() {
        return usuarioService.getTodosUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioPorId(@PathVariable Long id) {
        return usuarioService.getUsuarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/adicionar")
    @Transactional
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        usuario.setDataCriacao(LocalDateTime.now());
        return usuarioService.criarUsuario(usuario);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Usuario> editarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioService.editarUsuario(id, usuarioAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/topicos/{topicoId}/mensagens")
    public ResponseEntity<MensagemDTO> criarMensagemEmTopico(
            @PathVariable Long topicoId,
            @RequestBody Mensagem mensagem,
            @AuthenticationPrincipal UsuarioPrincipal usuarioPrincipal) {

        if (usuarioPrincipal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Usuario usuarioAutenticado = usuarioPrincipal.getUsuario();

        Optional<Topico> topicoOpt = topicoService.getTopicoPorId(topicoId);
        if (topicoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        mensagem.setUsuario(usuarioAutenticado);
        mensagem.setTopico(topicoOpt.get());
        mensagem.setDataCriacao(LocalDateTime.now());

        Mensagem nova = mensagemService.criarMensagem(mensagem);

        MensagemDTO novaDTO = MensagemDTO.fromEntity(nova);

        return ResponseEntity.status(HttpStatus.CREATED).body(novaDTO);
    }


}

