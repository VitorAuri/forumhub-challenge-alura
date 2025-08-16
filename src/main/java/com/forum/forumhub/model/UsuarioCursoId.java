package com.forum.forumhub.model;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioCursoId implements Serializable {

    private Long usuario;
    private Long curso;

    public UsuarioCursoId() {}

    public UsuarioCursoId(Long usuario, Long curso) {
        this.usuario = usuario;
        this.curso = curso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioCursoId that = (UsuarioCursoId) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(curso, that.curso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, curso);
    }
}

