package com.remedios.amber.curso.controllers.usuarios;

import com.remedios.amber.curso.dtos.security.DadosTokenJWT;
import com.remedios.amber.curso.security.services.TokenService;
import com.remedios.amber.curso.dtos.usuarios.UsuarioAutenticacaoDTO;
import com.remedios.amber.curso.dtos.usuarios.UsuarioCreateDTO;
import com.remedios.amber.curso.dtos.usuarios.UsuarioDetalhamentoDTO;
import com.remedios.amber.curso.entities.usuarios.Usuario;
import com.remedios.amber.curso.repositories.usuarios.UsuarioRepository;
import com.remedios.amber.curso.services.usuarios.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private UsuarioService service;

    @PostMapping("/login")
    @Operation(summary = "Logar Usuário",
            description ="Logar Usuário",
            tags = {"Usuários"})
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid UsuarioAutenticacaoDTO dados){
        return new ResponseEntity<>(service.efetuarLogin(dados), HttpStatus.OK);
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar Usuário",
            description ="Registrar Usuário",
            tags = {"Usuários"})
    public ResponseEntity<?> register(@RequestBody @Valid UsuarioCreateDTO dados, UriComponentsBuilder uriBuilder){
        UsuarioDetalhamentoDTO usuario = service.register(dados);

        URI uri = uriBuilder.path("/auth/{id}").buildAndExpand(usuario.id()).toUri();

        return ResponseEntity.created(uri).body(usuario);
    }

    @GetMapping()
    @Operation(summary = "Buscar Usuários",
            description ="Buscar Usuários",
            tags = {"Usuários"})
    public ResponseEntity<List<UsuarioDetalhamentoDTO>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
}
