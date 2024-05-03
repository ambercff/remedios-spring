package com.remedios.amber.curso.controllers.remedios;

import com.remedios.amber.curso.dtos.remedios.RemedioUpdateDTO;
import com.remedios.amber.curso.dtos.remedios.RemedioCreateDTO;
import com.remedios.amber.curso.dtos.remedios.RemedioDetalhamentoDTO;
import com.remedios.amber.curso.dtos.remedios.RemedioListagemDTO;
import com.remedios.amber.curso.entities.remedios.Remedio;
import com.remedios.amber.curso.repositories.remedios.RemedioRepository;
import com.remedios.amber.curso.services.remedios.RemedioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController // Indicando para o Spring que ele tem que inicilizar essa classe como um controller
@RequestMapping("/remedios") // Mapear o endpoint
@SecurityRequirement(name= "bearer-key")
public class RemedioController {

    @Autowired // Com a anotação 'Autowired' todos os métodos do repository podem ser utilizados nessa classe
    private RemedioService service;


    @PostMapping
    @Operation(summary = "Cadastrar Remédio",
            description ="Cadastrar Remédio",
            tags = {"Remédios"})
    @Transactional // Responsável pelo rollback se caso algo tiver errado
    // No parâmetro recebemos os dados enviados pelo user
    // Temos que utilizar o RequestBody para indicar de onde vem esse parâmetro

    // O DTO é uma forma de encapsular as informações enviadas pelo usuário dentro de uma classe
    // Dentro dessa classe você pode selecionar o que você gostaria de retornar, ex: nome, cpf, etc.

    public ResponseEntity<RemedioDetalhamentoDTO> cadastrar(@RequestBody @Valid RemedioCreateDTO dados,
                                                            UriComponentsBuilder uriBuilder){
        RemedioDetalhamentoDTO remedio = service.cadastro(dados);

        URI uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.id()).toUri();

        // Dentro do created passamos a URI para acessar o objeto criado. No body passamos o detalhamento
        // do objeto criado, ou seja, o dto.
        return ResponseEntity.created(uri).body(remedio);
    }

    @GetMapping
    @Operation(summary = "Listar Remédios",
            description ="Listar Remédios",
            tags = {"Remédios"})
    public ResponseEntity<List<RemedioListagemDTO>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Atualizar Remédio",
            description ="Atualizar Remédio",
            tags = {"Remédios"})
    public ResponseEntity<RemedioDetalhamentoDTO> atualizar(@RequestBody @Valid RemedioUpdateDTO dados) {
        return new ResponseEntity<>(service.atualizar(dados), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Inativar Remédio",
            description ="Inativar Remédio",
            tags = {"Remédios"})
    public ResponseEntity<RemedioDetalhamentoDTO> inativar(@PathVariable Long id) {
        return new ResponseEntity<>(service.inativar(id), HttpStatus.OK);
    }

    @PutMapping("/reativar/{id}")
    @Transactional
    @Operation(summary = "Reativar Remédio",
            description ="Reativar Remédio",
            tags = {"Remédios"})
    public ResponseEntity<RemedioDetalhamentoDTO> reativar(@PathVariable Long id) {
        return new ResponseEntity<>(service.reativar(id), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "Detalhes Remédio",
            description ="Detalhes Remédio",
            tags = {"Remédios"})
    public ResponseEntity<RemedioDetalhamentoDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }
}
