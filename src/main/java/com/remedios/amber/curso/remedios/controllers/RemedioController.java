package com.remedios.amber.curso.remedios.controllers;

import com.remedios.amber.curso.remedios.dtos.DadosCadastroRemedio;
import com.remedios.amber.curso.remedios.dtos.DadosListagemRemedio;
import com.remedios.amber.curso.remedios.entities.Remedio;
import com.remedios.amber.curso.remedios.repositories.RemedioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indicando para o Spring que ele tem que inicilizar essa classe como um controller
@RequestMapping("/remedios") // Mapear o endpoint
public class RemedioController {

    @Autowired // Com a anotação 'Autowired' todos os métodos do repository podem ser utilizados nessa classe
    private RemedioRepository repository;
    @PostMapping
    @Transactional // Responsável pelo rollback se caso algo tiver errado
    // No parâmetro recebemos os dados enviados pelo user
    // Temos que utilizar o RequestBody para indicar de onde vem esse parâmetro

    // O DTO é uma forma de encapsular as informações enviadas pelo usuário dentro de uma classe
    // Dentro dessa classe você pode selecionar o que você gostaria de retornar, ex: nome, cpf, etc.

    public void cadastrar(@RequestBody @Valid DadosCadastroRemedio dados){
        repository.save(new Remedio(dados));
    }

    @GetMapping
    public List<DadosListagemRemedio> getAll(){
        // A sintaxe DadosListagemRemedio::new serve para chamar o construtor
        return repository.findAll().stream().map(DadosListagemRemedio::new).toList();
    }
}
