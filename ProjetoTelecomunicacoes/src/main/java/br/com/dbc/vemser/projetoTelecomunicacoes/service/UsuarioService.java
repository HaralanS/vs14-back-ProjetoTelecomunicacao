package br.com.dbc.vemser.projetoTelecomunicacoes.service;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.LoginDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.UsuarioCreateDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.UsuarioDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.UsuarioEntity;
import br.com.dbc.vemser.projetoTelecomunicacoes.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.projetoTelecomunicacoes.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    @Value("${jwt.secret}")
    private String secret;

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    public Optional<UsuarioEntity> findByLoginAndSenha(String login, String senha) {
        return usuarioRepository.findByLoginAndSenha(login, senha);
    }

    public UsuarioDTO create(LoginDTO loginDTO) throws RegraDeNegocioException {
        UsuarioEntity usuarioEntity = objectMapper.convertValue(loginDTO, UsuarioEntity.class);
        StandardPasswordEncoder encoder = new StandardPasswordEncoder(secret);
        usuarioEntity.setSenha(encoder.encode(usuarioEntity.getSenha()));
        return objectMapper.convertValue(usuarioRepository.save(usuarioEntity), UsuarioDTO.class);
    }

    public Optional<UsuarioEntity>findByLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

    public Optional<UsuarioEntity>findById(Integer user){
        return usuarioRepository.findById(user);
    }
}
