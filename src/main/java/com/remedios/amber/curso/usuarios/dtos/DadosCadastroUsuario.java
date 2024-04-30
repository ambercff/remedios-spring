package com.remedios.amber.curso.usuarios.dtos;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(@NotBlank String nome, @NotBlank String login, @NotBlank String senha) {
}
