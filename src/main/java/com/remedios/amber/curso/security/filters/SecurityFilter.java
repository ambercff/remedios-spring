package com.remedios.amber.curso.security.filters;

import com.remedios.amber.curso.repositories.usuarios.UsuarioRepository;
import com.remedios.amber.curso.security.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
// A classe 'OncePerRequestFilter' tem como objetivo aplicar um filtro por requisição
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    // Existe uma cadeia de filtros, quando um filtro é executado, temos que chamar outro dentro dele,
    // se não o programa para de rodar
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {

        String tokenJWT = recuperarToken(request);

        if (tokenJWT != null){
            String subject = tokenService.getSubject(tokenJWT);
            UserDetails usuario = repository.findByLogin(subject);

            // Forçando a autenticação do usuário
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
