package br.com.dbc.vemser.projetoTelecomunicacoes.repository;


import br.com.dbc.vemser.projetoTelecomunicacoes.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    Optional<UsuarioEntity> findByLoginAndSenha(String login, String senha);
    Optional<UsuarioEntity> findByLogin(String login);
    Optional<UsuarioEntity> findById(Integer user);
}
