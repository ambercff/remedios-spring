package com.remedios.amber.curso.controllers;

import com.remedios.amber.curso.remedio.DadosCadastroRemedio;
import com.remedios.amber.curso.remedio.Remedio;
import com.remedios.amber.curso.remedio.RemedioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Indicando para o Spring que ele tem que inicilizar essa classe como um controller
@RequestMapping("/remedios") // Mapear o endpoint
public class RemedioController {

    @Autowired // Com a anotação 'Autowired' todos os métodos do repository podem ser utilizados nessa classe
    private RemedioRepository repository;
    @PostMapping
    // No parâmetro recebemos os dados enviados pelo user
    // Temos que utilizar o RequestBody para indicar de onde vem esse parâmetro

    // O DTO é uma forma de encapsular as informações enviadas pelo usuário dentro de uma classe
    // Dentro dessa classe você pode selecionar o que você gostaria de retornar, ex: nome, cpf, etc.

    public void cadastrar(@RequestBody DadosCadastroRemedio dados){
        repository.save(new Remedio(dados));
    }
}
