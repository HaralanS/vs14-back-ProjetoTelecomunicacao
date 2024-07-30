package br.com.dbc.vemser.projetoTelecomunicacoes.service;

import br.com.dbc.vemser.projetoTelecomunicacoes.entity.UsuarioEntity;
import br.com.dbc.vemser.projetoTelecomunicacoes.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    @Value("${jwt.secret}")
    private String secret;

    private final UsuarioRepository usuarioRepository;

    public Optional<UsuarioEntity> findByLoginAndSenha(String login, String senha) {
        return usuarioRepository.findByLoginAndSenha(login, senha);
    }

    public UsuarioEntity create(UsuarioEntity usuarioEntity) {
        StandardPasswordEncoder encoder = new StandardPasswordEncoder(secret);
        usuarioEntity.setSenha(encoder.encode(usuarioEntity.getPassword()));
        return usuarioRepository.save(usuarioEntity);

    }

    public Optional<UsuarioEntity>findByLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

    public Optional<UsuarioEntity>findById(Integer user){
        return usuarioRepository.findById(user);
    }
}
