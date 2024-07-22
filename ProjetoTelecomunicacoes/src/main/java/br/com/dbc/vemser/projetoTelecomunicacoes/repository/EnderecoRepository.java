package br.com.dbc.vemser.projetoTelecomunicacoes.repository;

import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Endereco;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.TipoEndereco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class EnderecoRepository {

    public List<Endereco> list() {
        List<Endereco> enderecos = new ArrayList<>();
        String query = "SELECT * FROM tele_comunicacoes.endereco";

        try (Connection connection = ConexaoBancoDeDados.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setIdEndereco(rs.getInt("id_endereco"));
                endereco.setIdPessoa(rs.getInt("id_pessoa"));
                endereco.setTipo(TipoEndereco.valueOf(rs.getString("tipo")));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setNumero(rs.getInt("numero"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setCep(rs.getString("cep"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setPais(rs.getString("pais"));
                enderecos.add(endereco);
            }
        } catch (SQLException e) {
            log.error("Erro ao listar endereços", e);
        }
        return enderecos;
    }

    public Endereco create(Integer idPessoa, Endereco endereco) {

        String query = "INSERT INTO tele_comunicacoes.endereco (id_pessoa, tipo, logradouro, numero, complemento, cep, cidade, estado, pais) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, idPessoa);
            pstmt.setString(2, endereco.getTipo().name());
            pstmt.setString(3, endereco.getLogradouro());
            pstmt.setInt(4, endereco.getNumero());
            pstmt.setString(5, endereco.getComplemento());
            pstmt.setString(6, endereco.getCep());
            pstmt.setString(7, endereco.getCidade());
            pstmt.setString(8, endereco.getEstado());
            pstmt.setString(9, endereco.getPais());

            pstmt.executeUpdate();

            } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        Endereco endereco2 = getEnderecoByIdPessoa(idPessoa);
        endereco2.setIdPessoa(idPessoa);
        return endereco2;
    }

    public Endereco update(Integer id, Endereco enderecoAtualizar, Endereco enderecoRecuperado) {
        String query = "UPDATE tele_comunicacoes.endereco SET tipo = ?, logradouro = ?, numero = ?, complemento = ?, cep = ?, cidade = ?, estado = ?, pais = ? WHERE id_endereco = ?";
        try (Connection connection = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, enderecoAtualizar.getTipo().name());
            pstmt.setString(2, enderecoAtualizar.getLogradouro());
            pstmt.setInt(3, enderecoAtualizar.getNumero());
            pstmt.setString(4, enderecoAtualizar.getComplemento());
            pstmt.setString(5, enderecoAtualizar.getCep());
            pstmt.setString(6, enderecoAtualizar.getCidade());
            pstmt.setString(7, enderecoAtualizar.getEstado());
            pstmt.setString(8, enderecoAtualizar.getPais());
            pstmt.setInt(9, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Erro ao atualizar endereço", e);
        }
        return enderecoAtualizar;
    }

    public Endereco getEnderecoByIdPessoa(Integer idPessoa) {

        Connection conn = null;
        Endereco endereco = new Endereco();

        try {

            conn = ConexaoBancoDeDados.getConnection();

            String query = "SELECT * FROM tele_comunicacoes.endereco WHERE id_pessoa = ?";

            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, idPessoa);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                endereco.setIdEndereco(rs.getInt("id_endereco"));
                endereco.setIdPessoa(rs.getInt("id_pessoa"));
                endereco.setTipo(TipoEndereco.valueOf(rs.getString("tipo")));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setNumero(rs.getInt("numero"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setCep(rs.getString("cep"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setPais(rs.getString("pais"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return endereco;

    }

}
