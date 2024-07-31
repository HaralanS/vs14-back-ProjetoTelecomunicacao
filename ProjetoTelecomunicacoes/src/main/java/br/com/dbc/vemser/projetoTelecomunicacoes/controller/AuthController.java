package br.com.dbc.vemser.projetoTelecomunicacoes.controller;


import br.com.dbc.vemser.projetoTelecomunicacoes.dto.LoginDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.UsuarioDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.UsuarioEntity;
import br.com.dbc.vemser.projetoTelecomunicacoes.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.projetoTelecomunicacoes.security.TokenService;
import br.com.dbc.vemser.projetoTelecomunicacoes.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@Validated
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioService usuarioService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public String auth(@RequestBody @Valid LoginDTO loginDTO) throws RegraDeNegocioException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getLogin(),
                        loginDTO.getSenha()
                );

        Authentication authentication =
                authenticationManager.authenticate(
                        usernamePasswordAuthenticationToken);

        UsuarioEntity usuarioValidado = (UsuarioEntity) authentication.getPrincipal();

        return tokenService.gerarTokenJWT(usuarioValidado);
    }

    @PostMapping("/create")
    public UsuarioDTO create(@RequestBody @Valid LoginDTO loginDTO) throws RegraDeNegocioException {
        UsuarioDTO usuarioCriado = usuarioService.create(loginDTO);
        return usuarioCriado;
    }
}
