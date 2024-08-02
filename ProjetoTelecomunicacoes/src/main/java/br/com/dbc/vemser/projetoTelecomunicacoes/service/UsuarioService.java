package br.com.dbc.vemser.projetoTelecomunicacoes.service;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.LoginDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.UsuarioDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.CargoEntity;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Cliente;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.UsuarioEntity;
import br.com.dbc.vemser.projetoTelecomunicacoes.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.projetoTelecomunicacoes.repository.ClienteRepository;
import br.com.dbc.vemser.projetoTelecomunicacoes.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Value("${jwt.secret}")
    private String secret;

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private final ClienteRepository clienteRepository;

    public Optional<UsuarioEntity> findByLoginAndSenha(String login, String senha) {
        return usuarioRepository.findByLoginAndSenha(login, senha);
    }

    public Integer getIdLoggedUser() {
        return (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Optional<UsuarioEntity> getLoggedUser() throws RegraDeNegocioException {
        return findById(getIdLoggedUser());
    }

    public UsuarioDTO create(LoginDTO loginDTO) throws RegraDeNegocioException {
        UsuarioEntity usuarioEntity = objectMapper.convertValue(loginDTO, UsuarioEntity.class);
        Set<CargoEntity> cargos = new HashSet<>();
        cargos.add(createUserRole());
        usuarioEntity.setCargos(cargos);

        usuarioEntity.setSenha(encodePassword(usuarioEntity.getSenha()));
        return objectMapper.convertValue(usuarioRepository.save(usuarioEntity), UsuarioDTO.class);
    }


    public Optional<UsuarioEntity> findByLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

    public Optional<UsuarioEntity> findById(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    private String encodePassword(String senha) {
        if(senha == null) {
            throw new IllegalArgumentException("Raw password cannot be null");
        }
        StandardPasswordEncoder encoder = new StandardPasswordEncoder(secret);
        return encoder.encode(senha);
    }

    private CargoEntity createUserRole() {
        CargoEntity cargoEntity = new CargoEntity();
        cargoEntity.setIdCargo(2);
        cargoEntity.setNome("ROLE_USUARIO");
        return cargoEntity;
    }
}
