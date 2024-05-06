package com.remedios.amber.curso.dtos.usuarios;

import com.remedios.amber.curso.dtos.usuarios.enums.UserRole;
import com.remedios.amber.curso.entities.usuarios.Usuario;
import jakarta.validation.constraints.NotBlank;

public record UserRoleDTO(@NotBlank UserRole role) {
}
