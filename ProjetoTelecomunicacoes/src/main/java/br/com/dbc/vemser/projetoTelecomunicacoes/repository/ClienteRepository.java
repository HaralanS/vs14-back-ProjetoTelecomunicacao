package br.com.dbc.vemser.projetoTelecomunicacoes.repository;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.FaturaCreateDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.FaturaDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Fatura;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Cliente;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.planos.*;
import br.com.dbc.vemser.projetoTelecomunicacoes.service.FaturaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Repository
public class ClienteRepository {

    List<Cliente> clientes = new ArrayList<>();
    List<Cliente> clientePorNome = new ArrayList<>();
    List<Cliente> clienteCreateResponse = new ArrayList<>();
    List<Cliente> clienteUpdateResponse = new ArrayList<>();
    List<Cliente> clienteDeleteResponse = new ArrayList<>();


    private AtomicInteger COUNTER = new AtomicInteger();
    private final FaturaRepository faturaRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //18/10/2020

    public ClienteRepository(FaturaRepository faturaRepository) {
        this.faturaRepository = faturaRepository;
    }

    public List<Cliente> getAllClientes() {
        clientes = new ArrayList<>();
        String sql = "SELECT * FROM tele_comunicacoes.TB_CLIENTE";

        try (Connection connection = ConexaoBancoDeDados.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdPessoa(Integer.valueOf(resultSet.getInt("id_cliente")));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setDataNascimento(resultSet.getDate("dt_nascimento").toLocalDate());
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setNumeroTelefone(Long.valueOf(resultSet.getLong("numero_telefone")));
                cliente.setTipoDePlano(TipoDePlano.valueOf(resultSet.getString("tipo_plano")));
                cliente.setStatus(Boolean.valueOf(resultSet.getBoolean("status")));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public List<Cliente> getAClientByName(String nome) throws SQLException {
        clientePorNome = new ArrayList<>();
        String sql = "SELECT * FROM tele_comunicacoes.TB_CLIENTE c WHERE c.nome = ?";
        Connection connection = ConexaoBancoDeDados.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, nome);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdPessoa(Integer.valueOf(resultSet.getInt("id_cliente")));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setDataNascimento(resultSet.getDate("dt_nascimento").toLocalDate());
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setNumeroTelefone(Long.valueOf(resultSet.getLong("numero_telefone")));
                cliente.setTipoDePlano(TipoDePlano.valueOf(resultSet.getString("tipo_plano")));
                cliente.setStatus(Boolean.valueOf(resultSet.getBoolean("status")));
                clientePorNome.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientePorNome;
    }

    public List<Cliente> getClientById(Integer id) throws SQLException {
        clientes = new ArrayList<>();
        String sql = "SELECT * FROM tele_comunicacoes.TB_CLIENTE c WHERE c.id_cliente = ?";
        Connection connection = ConexaoBancoDeDados.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdPessoa(Integer.valueOf(resultSet.getInt("id_cliente")));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setDataNascimento(resultSet.getDate("dt_nascimento").toLocalDate());
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setNumeroTelefone(Long.valueOf(resultSet.getLong("numero_telefone")));
                cliente.setTipoDePlano(TipoDePlano.valueOf(resultSet.getString("tipo_plano")));
                cliente.setStatus(Boolean.valueOf(resultSet.getBoolean("status")));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    // haralam que entende melhor esse método
    public Cliente create(Cliente cliente) throws Exception {

        log.debug("Entrando na PessoaRepository");
        Connection conn = null;
        String sql;
        Integer idCliente = null;

        String cpf = cliente.getCpf();

        try {

            conn = ConexaoBancoDeDados.getConnection();

            sql = "INSERT INTO tele_comunicacoes.TB_CLIENTE " +
                    "(nome, dt_nascimento, cpf, email, numero_telefone, tipo_plano, status)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setDate(2, Date.valueOf(cliente.getDataNascimento()));
            preparedStatement.setString(3, cliente.getCpf());
            preparedStatement.setString(4, cliente.getEmail());
            preparedStatement.setLong(5, cliente.getNumeroTelefone());
            preparedStatement.setString(6, cliente.getTipoDePlano().toString());
            preparedStatement.setBoolean(7, true);

            preparedStatement.executeUpdate();


            // RETORNANDO ID GERADO PARA O CLIENTE E PASSAR PARA A FATURA
            sql = "SELECT id_cliente FROM tele_comunicacoes.tb_cliente WHERE cpf = ?";
            preparedStatement = conn.prepareStatement(sql);

            System.out.println();

            preparedStatement.setString(1, cpf);

            ResultSet resultSet = preparedStatement.executeQuery();
            log.debug("Result set funcionando!");


            while (resultSet.next()) {
                idCliente = (Integer) resultSet.getInt("id_cliente");
            }

            cliente.setIdPessoa(idCliente);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


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
            Fatura fatura = new Fatura(idCliente, dataVencimento, null, plano.getValor(), 0, (Integer) (i+1));
            log.debug("Criando fatura após criar cliente");
            faturaRepository.create(fatura);
        }


        return cliente;
    }

    public List<Cliente> update(Integer id, Cliente cliente) throws SQLException {
        log.debug("Entrando na PessoaRepository");
        clienteUpdateResponse = new ArrayList<>();

        String sql = "UPDATE tele_comunicacoes.TB_CLIENTE SET nome = ?, cpf = ?, email = ?, numero_telefone = ? WHERE id_cliente = ?";
        Connection connection = ConexaoBancoDeDados.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        cliente.setIdPessoa(id);

        preparedStatement.setString(1, cliente.getNome());
        preparedStatement.setString(2, cliente.getCpf());
        preparedStatement.setString(3, cliente.getEmail());
        preparedStatement.setLong(4, cliente.getNumeroTelefone());
        preparedStatement.setInt(5, id);

        preparedStatement.execute();
        clienteUpdateResponse.add(cliente);
        return clienteUpdateResponse;
    }

    public void delete(Cliente cliente, Integer id) throws SQLException {
        log.debug("Entrando na PessoaRepository");
        clienteDeleteResponse = new ArrayList<>();
        cliente.setStatus(false);

        String sql = "DELETE FROM tele_comunicacoes.TB_CLIENTE WHERE id_cliente = ?";
        Connection connection = ConexaoBancoDeDados.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        preparedStatement.execute();
        clienteDeleteResponse.add(cliente);

    }

}