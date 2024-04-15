package com.remedios.amber.curso.remedio;

// DTO, usado para encapsular os dados transferidos entre
// o controller e a camada de persistência
public record DadosCadastroRemedio(String nome, Via via,String lote, String quantidade,
                                   String validade, Laboratorio laboratorio) {
}
