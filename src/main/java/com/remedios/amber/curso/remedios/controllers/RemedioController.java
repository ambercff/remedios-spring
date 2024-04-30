package com.remedios.amber.curso.remedios.controllers;

import com.remedios.amber.curso.remedios.dtos.DadosAtualizarRemedio;
import com.remedios.amber.curso.remedios.dtos.DadosCadastroRemedio;
import com.remedios.amber.curso.remedios.dtos.DadosDetalhamentoRemedio;
import com.remedios.amber.curso.remedios.dtos.DadosListagemRemedio;
import com.remedios.amber.curso.remedios.entities.Remedio;
import com.remedios.amber.curso.remedios.repositories.RemedioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController // Indicando para o Spring que ele tem que inicilizar essa classe como um controller
@RequestMapping("/remedios") // Mapear o endpoint
@SecurityRequirement(name= "bearer-key")
public class RemedioController {

    @Autowired // Com a anotação 'Autowired' todos os métodos do repository podem ser utilizados nessa classe
    private RemedioRepository repository;
    @PostMapping
    @Transactional // Responsável pelo rollback se caso algo tiver errado
    // No parâmetro recebemos os dados enviados pelo user
    // Temos que utilizar o RequestBody para indicar de onde vem esse parâmetro

    // O DTO é uma forma de encapsular as informações enviadas pelo usuário dentro de uma classe
    // Dentro dessa classe você pode selecionar o que você gostaria de retornar, ex: nome, cpf, etc.

    public ResponseEntity<DadosDetalhamentoRemedio> cadastrar(@RequestBody @Valid DadosCadastroRemedio dados,
                                          UriComponentsBuilder uriBuilder){
        var remedio = new Remedio(dados);

        repository.save(remedio);

        var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();

        // Dentro do created passamos a URI para acessar o objeto criado. No body passamos o detalhamento
        // do objeto criado, ou seja, o dto.
        return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedio(remedio));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemRemedio>> getAll(){
        // A sintaxe DadosListagemRemedio::new serve para chamar o construtor
        var lista =  repository.findAllByAtivoTrue().stream().map(DadosListagemRemedio::new).toList();

        return ResponseEntity.ok(lista);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
        var remedio = repository.getReferenceById((dados.id()));
        remedio.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        var remedio = repository.getReferenceById(id);
        remedio.setAtivo(false);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reativar/{id}")
    @Transactional
    public ResponseEntity<Void> reativar(@PathVariable Long id) {
        var remedio = repository.getReferenceById(id);
        remedio.setAtivo(true);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosDetalhamentoRemedio> getById(@PathVariable Long id) {
        var remedio = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }
}
