package com.remedios.amber.curso.dtos.remedios;

import com.remedios.amber.curso.dtos.remedios.enums.Laboratorio;
import com.remedios.amber.curso.dtos.remedios.enums.Via;
import jakarta.validation.constraints.NotNull;

public record RemedioUpdateDTO(@NotNull Long id, String nome, Via via, Laboratorio laboratorio) {
}
