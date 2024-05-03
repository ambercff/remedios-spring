package com.remedios.amber.curso.dtos.usuarios;

import jakarta.validation.constraints.NotBlank;

public record UsuarioCreateDTO(@NotBlank String nome, @NotBlank String login, @NotBlank String senha) {
}
