package com.forum.forumhub.controller;

import com.forum.forumhub.security.JwtService;
import com.forum.forumhub.service.UsuarioDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioDetailsService usuarioDetailsService;

    public record LoginRequest(String email, String senha) {}
    public record TokenResponse(String token) {}

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          UsuarioDetailsService usuarioDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.usuarioDetailsService = usuarioDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        try {
            Authentication auth = authenticationManager.authenticate(authToken);
            UserDetails userDetails = usuarioDetailsService.loadUserByUsername(dto.email());
            String token = jwtService.gerarToken(userDetails);
            return ResponseEntity.ok(new TokenResponse(token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciais inválidas"));
        }
    }
}


