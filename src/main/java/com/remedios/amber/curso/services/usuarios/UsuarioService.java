package com.remedios.amber.curso.services.usuarios;

import com.remedios.amber.curso.dtos.remedios.RemedioDetalhamentoDTO;
import com.remedios.amber.curso.dtos.security.DadosTokenJWT;
import com.remedios.amber.curso.dtos.usuarios.UserRoleDTO;
import com.remedios.amber.curso.dtos.usuarios.UsuarioAutenticacaoDTO;
import com.remedios.amber.curso.dtos.usuarios.UsuarioCreateDTO;
import com.remedios.amber.curso.dtos.usuarios.UsuarioDetalhamentoDTO;
import com.remedios.amber.curso.dtos.usuarios.enums.UserRole;
import com.remedios.amber.curso.entities.usuarios.Usuario;
import com.remedios.amber.curso.exceptions.NotFoundException;
import com.remedios.amber.curso.repositories.usuarios.UsuarioRepository;
import com.remedios.amber.curso.security.filters.SecurityFilter;
import com.remedios.amber.curso.security.services.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityFilter securityFilter;

    public DadosTokenJWT efetuarLogin(UsuarioAutenticacaoDTO data) {

        UserDetails usuario = repository.findByLogin(data.login());

        System.out.println("Usuario: " + usuario);

        if (usuario == null) {
            System.out.println("ENTREI");
            throw new BadCredentialsException("Usuário inexistente ou senha inválida. Tente novamente!");
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(data.login()
                , data.senha());

        Authentication autenticacao = manager.authenticate(token);

        var tokenJWT = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        return new DadosTokenJWT(tokenJWT);

    }

    public UsuarioDetalhamentoDTO register(UsuarioCreateDTO data) {

        if(repository.findByLogin(data.login()) != null) {
            throw new IllegalArgumentException("Login já existente!");
        }

        Usuario usuario = new Usuario(data, passwordEncoder.encode(data.senha()));

        repository.save(usuario);

        return new UsuarioDetalhamentoDTO(usuario);
    }

    public List<UsuarioDetalhamentoDTO> getAll(){
        return repository.findAll().stream().map(UsuarioDetalhamentoDTO::new).toList();
    }

    public UsuarioDetalhamentoDTO setPrivilege(Long id, UserRoleDTO data){
        Usuario usuario = repository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado!"));
        usuario.setRole(data.role());

        return new UsuarioDetalhamentoDTO(usuario);
    }
}
