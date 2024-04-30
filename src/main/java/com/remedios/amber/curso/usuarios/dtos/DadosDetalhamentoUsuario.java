package com.remedios.amber.curso.usuarios.dtos;

import com.remedios.amber.curso.usuarios.entities.Usuario;

public record DadosDetalhamentoUsuario(Long id, String nome, String login) {

    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getLogin());
    }
}
