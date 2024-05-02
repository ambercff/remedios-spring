package com.remedios.amber.curso.controllers.usuarios;

import com.remedios.amber.curso.dtos.security.DadosTokenJWT;
import com.remedios.amber.curso.security.services.TokenService;
import com.remedios.amber.curso.dtos.usuarios.DadosAutenticacao;
import com.remedios.amber.curso.dtos.usuarios.DadosCadastroUsuario;
import com.remedios.amber.curso.dtos.usuarios.DadosDetalhamentoUsuario;
import com.remedios.amber.curso.entities.usuarios.Usuario;
import com.remedios.amber.curso.repositories.usuarios.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        Authentication autenticacao = manager.authenticate(token);
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

        URI uri = uriBuilder.path("/auth/{id}").buildAndExpand(usuario.getId()).toUri();

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
