package com.remedios.amber.curso.remedios.dtos;

import com.remedios.amber.curso.remedios.dtos.enums.Laboratorio;
import com.remedios.amber.curso.remedios.dtos.enums.Via;
import com.remedios.amber.curso.remedios.entities.Remedio;

import java.time.LocalDate;

public record DadosListagemRemedio(Long id, String nome, Via via, String lote, Laboratorio laboratorio,
LocalDate validade) {

    // Construtor
    public DadosListagemRemedio(Remedio remedio) {
        this(remedio.getId(), remedio.getNome(), remedio.getVia(), remedio.getLote()
                , remedio.getLaboratorio(), remedio.getValidade());
    }
}
