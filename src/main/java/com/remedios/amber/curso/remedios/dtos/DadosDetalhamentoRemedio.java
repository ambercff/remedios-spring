package com.remedios.amber.curso.remedios.dtos;

import com.remedios.amber.curso.remedios.dtos.enums.Laboratorio;
import com.remedios.amber.curso.remedios.dtos.enums.Via;
import com.remedios.amber.curso.remedios.entities.Remedio;

import java.time.LocalDate;

public record DadosDetalhamentoRemedio(Long id, String nome, Via via,
                                       String lote, int quantidade, LocalDate validade,
                                       Laboratorio laboratorio, Boolean ativo) {

    public DadosDetalhamentoRemedio(Remedio remedio){
        this(remedio.getId(),
                remedio.getNome(),
                remedio.getVia(),
                remedio.getLote(),
                remedio.getQuantidade(),
                remedio.getValidade(),
                remedio.getLaboratorio(),
                remedio.getAtivo());
    }
}
