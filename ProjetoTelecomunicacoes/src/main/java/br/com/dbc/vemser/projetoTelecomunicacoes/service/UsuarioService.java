package br.com.dbc.vemser.projetoTelecomunicacoes.service;

import br.com.dbc.vemser.projetoTelecomunicacoes.entity.UsuarioEntity;
import br.com.dbc.vemser.projetoTelecomunicacoes.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Optional<UsuarioEntity> findByLoginAndSenha(String login, String senha) {
        return usuarioRepository.findByLoginAndSenha(login, senha);
    }
}
