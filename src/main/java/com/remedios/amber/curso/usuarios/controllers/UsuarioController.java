package com.remedios.amber.curso.usuarios.controllers;

import com.remedios.amber.curso.security.dtos.DadosTokenJWT;
import com.remedios.amber.curso.security.TokenService;
import com.remedios.amber.curso.usuarios.dtos.DadosAutenticacao;
import com.remedios.amber.curso.usuarios.dtos.DadosCadastroUsuario;
import com.remedios.amber.curso.usuarios.dtos.DadosDetalhamentoUsuario;
import com.remedios.amber.curso.usuarios.entities.Usuario;
import com.remedios.amber.curso.usuarios.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name= "bearer-key")
public class UsuarioController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @Operation(summary = "Logar Usuário",
            description ="Logar Usuário",
            tags = {"Usuários"})
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var autenticacao = manager.authenticate(token);
        var tokenJWT = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar Usuário",
            description ="Registrar Usuário",
            tags = {"Usuários"})
    public ResponseEntity<?> register(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){
        Usuario usuario = new Usuario(dados, passwordEncoder.encode(dados.senha()));

        repository.save(usuario);

        var uri = uriBuilder.path("/auth/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }



    @GetMapping()
    @Operation(summary = "Buscar Usuários",
            description ="Buscar Usuários",
            tags = {"Usuários"})
    public ResponseEntity<List<DadosDetalhamentoUsuario>> getAll(){
        List<DadosDetalhamentoUsuario> lista =
                repository.findAll().stream().map(DadosDetalhamentoUsuario::new).toList();

        return ResponseEntity.ok(lista);
    }
}
