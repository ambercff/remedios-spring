package com.remedios.amber.curso.dtos.usuarios;

import com.remedios.amber.curso.entities.usuarios.Usuario;

public record UsuarioDetalhamentoDTO(Long id, String nome, String login) {

    public UsuarioDetalhamentoDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getLogin());
    }
}
