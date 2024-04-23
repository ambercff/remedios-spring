package com.remedios.amber.curso.remedios.dtos;

import com.remedios.amber.curso.remedios.dtos.enums.Laboratorio;
import com.remedios.amber.curso.remedios.dtos.enums.Via;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarRemedio(@NotNull Long id, String nome, Via via, Laboratorio laboratorio) {
}
