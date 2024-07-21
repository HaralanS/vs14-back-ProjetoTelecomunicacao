package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.Fatura;
import br.com.dbc.vemser.pessoaapi.entity.Cliente;
import br.com.dbc.vemser.pessoaapi.entity.planos.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class ClienteRepository {
    private static List<Cliente> listaClientes = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();
    private final FaturaRepository faturaRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //18/10/2020

    public ClienteRepository(FaturaRepository faturaRepository) {
        this.faturaRepository = faturaRepository;

        listaClientes.add(new Cliente(COUNTER.incrementAndGet() /*1*/, "Maicon Gerardi", LocalDate.parse("10/10/1990", formatter), "12345678910", "juazin1@gmail.com", 1234567891, TipoDePlano.BASICO, true));
        listaClientes.add(new Cliente(COUNTER.incrementAndGet() /*2*/, "Charles Pereira", LocalDate.parse("08/05/1985", formatter), "12345678911", "juazin2@gmail.com", 1234567891, TipoDePlano.BASICO, true));
        listaClientes.add(new Cliente(COUNTER.incrementAndGet() /*3*/, "Marina Oliveira", LocalDate.parse("30/03/1970", formatter), "12345678912", "juazin3@gmail.com", 1234567891, TipoDePlano.BASICO, true));
        listaClientes.add(new Cliente(COUNTER.incrementAndGet() /*4*/, "Rafael Lazzari", LocalDate.parse("01/07/1990", formatter), "12345678916", "juazin4@gmail.com", 1234567891, TipoDePlano.BASICO, true));
        listaClientes.add(new Cliente(COUNTER.incrementAndGet() /*5*/, "Ana", LocalDate.parse("01/07/1990", formatter), "12345678917", "juazin5@gmail.com", 1234567891, TipoDePlano.BASICO, true));
    }

    public List<Cliente> list() {
        return listaClientes;
    }

    public List<Cliente> listByName(String nome) {
        return listaClientes.stream()
                .filter(pessoa -> pessoa.getNome().toUpperCase().contains(nome.toUpperCase()))
                .collect(Collectors.toList());
    }


    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM tele_comunicacoes.TB_CLIENTE";

        try (Connection connection = ConexaoBancoDeDados.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdPessoa(resultSet.getInt("idPessoa"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setDataNascimento(resultSet.getDate("dataNascimento").toLocalDate());
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setNumeroTelefone(resultSet.getInt("numeroTelefone"));
                cliente.setTipoDePlano(TipoDePlano.valueOf(resultSet.getString("tipoDePlano")));
                cliente.setStatus(resultSet.getBoolean("status"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public Cliente create(Cliente cliente) {
        log.debug("Entrando na PessoaRepository");
        cliente.setIdPessoa(COUNTER.incrementAndGet());
        Plano plano = null;
        switch (cliente.getTipoDePlano()){
            case BASICO:
                plano = new PlanoBasico();
                break;
            case MEDIUM:
                plano = new PlanoMedium();
                break;
            case PREMIUM:
                plano = new PlanoPremium();
                break;
        }
        LocalDate dataAtual = LocalDate.now();
        for (int i = 0; i < 12; i++) {
            LocalDate dataVencimento = dataAtual.plusMonths((i + 1));
            Fatura fatura = new Fatura(faturaRepository.getIdFatura(), cliente.getIdPessoa(), dataVencimento, null, plano.getValor(), 0, (i+1));
            faturaRepository.create(fatura);
        }
        listaClientes.add(cliente);
        return cliente;
    }

    public Cliente update(Integer id, Cliente clienteAtualizar, Cliente clienteRecuperada) {
        log.debug("Entrando na PessoaRepository");
        clienteRecuperada.setCpf(clienteAtualizar.getCpf());
        clienteRecuperada.setNome(clienteAtualizar.getNome());
        clienteRecuperada.setDataNascimento(clienteAtualizar.getDataNascimento());
        return clienteRecuperada;
    }

    public void delete(Cliente cliente) {
        log.debug("Entrando na PessoaRepository");
        cliente.setStatus(false);
    }

}