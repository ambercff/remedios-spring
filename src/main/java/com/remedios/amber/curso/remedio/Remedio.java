package com.remedios.amber.curso.remedio;

import jakarta.persistence.*;
import lombok.*;

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
    private String quantidade;
    private String validade;
    @Enumerated(EnumType.STRING)
    private Laboratorio laboratorio;

    public Remedio(DadosCadastroRemedio dados) {
        this.nome = dados.nome();
        this.via = dados.via();
        this.lote = dados.lote();
        this.quantidade = dados.quantidade();
        this.laboratorio = dados.laboratorio();
    }
}
