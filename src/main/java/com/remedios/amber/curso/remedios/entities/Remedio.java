package com.remedios.amber.curso.remedios.entities;

import com.remedios.amber.curso.remedios.dtos.enums.Laboratorio;
import com.remedios.amber.curso.remedios.dtos.enums.Via;
import com.remedios.amber.curso.remedios.dtos.DadosCadastroRemedio;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name="remedios")
@Table(name= "Remedio")
// Criando getters, setters e construtores
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Remedio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Via via;

    private String lote;
    private int quantidade;
    private LocalDate validade;
    @Enumerated(EnumType.STRING)
    private Laboratorio laboratorio;

    public Remedio(DadosCadastroRemedio dados) {
        this.nome = dados.nome();
        this.via = dados.via();
        this.lote = dados.lote();
        this.quantidade = dados.quantidade();
        this.validade = dados.validade();
        this.laboratorio = dados.laboratorio();
    }
}
