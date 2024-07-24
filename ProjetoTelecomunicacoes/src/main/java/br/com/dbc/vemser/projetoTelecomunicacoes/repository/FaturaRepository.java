package br.com.dbc.vemser.projetoTelecomunicacoes.repository;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.FaturaDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Fatura;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Integer> {

    List<FaturaDTO> findAllByIdCliente(Integer idFatura);
    void deleteByPessoaId(Integer pessoaId);

}
