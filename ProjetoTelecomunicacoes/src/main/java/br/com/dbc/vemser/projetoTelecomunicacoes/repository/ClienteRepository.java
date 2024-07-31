package br.com.dbc.vemser.projetoTelecomunicacoes.repository;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.FaturaCreateDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.FaturaDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Fatura;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Cliente;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.planos.*;
import br.com.dbc.vemser.projetoTelecomunicacoes.service.FaturaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findAllByNomeContainsIgnoreCase(String nome);

    @Query("SELECT DISTINCT c FROM tb_cliente c WHERE c.idCliente = :idPessoa")
    Optional<Cliente> findClienteTela1ById(@Param("idPessoa") Integer idPessoa);

    @Query("SELECT DISTINCT c FROM tb_cliente c WHERE c.idCliente = :idPessoa")
    Cliente findClienteUnique(@Param("idPessoa") Integer idPessoa);




}