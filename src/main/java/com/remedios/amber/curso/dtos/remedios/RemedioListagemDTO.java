package com.remedios.amber.curso.dtos.remedios;

import com.remedios.amber.curso.dtos.remedios.enums.Laboratorio;
import com.remedios.amber.curso.dtos.remedios.enums.Via;
import com.remedios.amber.curso.entities.remedios.Remedio;

import java.time.LocalDate;

public record RemedioListagemDTO(Long id, String nome, Via via, String lote, Laboratorio laboratorio,
                                 LocalDate validade) {

    // Construtor
    public RemedioListagemDTO(Remedio remedio) {
        this(remedio.getId(), remedio.getNome(), remedio.getVia(), remedio.getLote()
                , remedio.getLaboratorio(), remedio.getValidade());
    }
}
