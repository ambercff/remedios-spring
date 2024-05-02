package com.remedios.amber.curso.controllers.remedios;

import com.remedios.amber.curso.dtos.remedios.DadosAtualizarRemedio;
import com.remedios.amber.curso.dtos.remedios.DadosCadastroRemedio;
import com.remedios.amber.curso.dtos.remedios.DadosDetalhamentoRemedio;
import com.remedios.amber.curso.dtos.remedios.DadosListagemRemedio;
import com.remedios.amber.curso.entities.remedios.Remedio;
import com.remedios.amber.curso.repositories.remedios.RemedioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RemedioRepository repository;
    @PostMapping
    @Operation(summary = "Cadastrar Remédio",
            description ="Cadastrar Remédio",
            tags = {"Remédios"})
    @Transactional // Responsável pelo rollback se caso algo tiver errado
    // No parâmetro recebemos os dados enviados pelo user
    // Temos que utilizar o RequestBody para indicar de onde vem esse parâmetro

    // O DTO é uma forma de encapsular as informações enviadas pelo usuário dentro de uma classe
    // Dentro dessa classe você pode selecionar o que você gostaria de retornar, ex: nome, cpf, etc.

    public ResponseEntity<DadosDetalhamentoRemedio> cadastrar(@RequestBody @Valid DadosCadastroRemedio dados,
                                          UriComponentsBuilder uriBuilder){
        Remedio remedio = new Remedio(dados);

        repository.save(remedio);

        URI uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();

        // Dentro do created passamos a URI para acessar o objeto criado. No body passamos o detalhamento
        // do objeto criado, ou seja, o dto.
        return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedio(remedio));
    }

    @GetMapping
    @Operation(summary = "Listar Remédios",
            description ="Listar Remédios",
            tags = {"Remédios"})
    public ResponseEntity<List<DadosListagemRemedio>> getAll(){
        // A sintaxe DadosListagemRemedio::new serve para chamar o construtor
        List<DadosListagemRemedio> lista =  repository.findAllByAtivoTrue().stream().map(DadosListagemRemedio::new).toList();

        return ResponseEntity.ok(lista);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Atualizar Remédio",
            description ="Atualizar Remédio",
            tags = {"Remédios"})
    public ResponseEntity<DadosDetalhamentoRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
        Remedio remedio = repository.getReferenceById((dados.id()));
        remedio.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Inativar Remédio",
            description ="Inativar Remédio",
            tags = {"Remédios"})
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        Remedio remedio = repository.getReferenceById(id);
        remedio.setAtivo(false);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reativar/{id}")
    @Transactional
    @Operation(summary = "Reativar Remédio",
            description ="Reativar Remédio",
            tags = {"Remédios"})
    public ResponseEntity<Void> reativar(@PathVariable Long id) {
        Remedio remedio = repository.getReferenceById(id);
        remedio.setAtivo(true);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    @Operation(summary = "Detalhes Remédio",
            description ="Detalhes Remédio",
            tags = {"Remédios"})
    public ResponseEntity<DadosDetalhamentoRemedio> getById(@PathVariable Long id) {
        Remedio remedio = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }
}
