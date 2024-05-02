package com.remedios.amber.curso.dtos.remedios;

import com.remedios.amber.curso.dtos.remedios.enums.Laboratorio;
import com.remedios.amber.curso.dtos.remedios.enums.Via;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

// DTO, usado para encapsular os dados transferidos entre
// o controller e a camada de persistência
public record DadosCadastroRemedio(
        @NotBlank
        String nome,
        @Enumerated
        Via via,
        @NotBlank
        String lote,
        int quantidade,
        @Future
        LocalDate validade,
        @Enumerated
        Laboratorio laboratorio) {
}
