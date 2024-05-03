package com.remedios.amber.curso.dtos.remedios;

import com.remedios.amber.curso.dtos.remedios.enums.Laboratorio;
import com.remedios.amber.curso.dtos.remedios.enums.Via;
import com.remedios.amber.curso.entities.remedios.Remedio;

import java.time.LocalDate;

public record RemedioDetalhamentoDTO(Long id, String nome, Via via,
                                     String lote, int quantidade, LocalDate validade,
                                     Laboratorio laboratorio, Boolean ativo) {

    public RemedioDetalhamentoDTO(Remedio remedio){
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
