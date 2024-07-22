package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.Fatura;
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
public class FaturaRepository {

    private static List<Fatura> listaFaturas = new ArrayList<>();
    private AtomicInteger COUNTERFATURA = new AtomicInteger();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //18/10/2020

    public FaturaRepository(){

    }

    public List<Fatura> list() {
        return listaFaturas;
    }
    public List<Fatura> listByClient(Integer idCliente) {

        List<Fatura> faturasEncontradas = new ArrayList<>();
        Connection conn = null;

        try {
            conn = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM tele_comunicacoes.fatura WHERE id_cliente = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Fatura fatura = new Fatura();

                fatura.setIdFatura(rs.getInt("id_fatura"));
                fatura.setIdCliente(rs.getInt("id_cliente"));
                fatura.setDataVencimento(rs.getDate("dt_vencimento").toLocalDate());
                fatura.setDataBaixa(rs.getDate("dt_baixa").toLocalDate());
                fatura.setParcelaDoPlano(rs.getDouble("parcela_do_plano"));
                fatura.setValorPago(rs.getDouble("valor_pago"));
                fatura.setNumeroFatura(rs.getInt("numero_fatura"));

                faturasEncontradas.add(fatura);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return faturasEncontradas;
    }

    public int getIdFatura(){
        return COUNTERFATURA.incrementAndGet();
    }



        /*
            public List<Fatura> findById(Integer idFatura) {
        return listaFaturas.stream().filter(fatura -> fatura.getIdFatura() == idFatura).collect(Collectors.toList());
    }

        public Pessoa getPessoa(Integer id) throws Exception {
        Pessoa pessoaRecuperada = pessoaRepository.list().stream()
                .filter(pessoa -> pessoa.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada!"));
        return pessoaRecuperada;
    }
     */

    public Fatura create(Fatura fatura) {
        fatura.setIdFatura(COUNTERFATURA.incrementAndGet());
        listaFaturas.add(fatura);
        return fatura;
    }

    public Fatura pagarFatura(Integer idCliente, Integer numeroFatura, double valorBaixa, LocalDate dataBaixa) {

        Connection conn = null;

        try {
            conn = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM tele_comunicacoes.fatura WHERE id_cliente = ? AND numero_fatura = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, idCliente);
            preparedStatement.setInt(2, numeroFatura);

            ResultSet rs = preparedStatement.executeQuery();
            Fatura fatura = new Fatura();
            while (rs.next()) {

                fatura.setIdFatura(rs.getInt("id_fatura"));
                fatura.setIdCliente(rs.getInt("id_cliente"));
                fatura.setDataVencimento(rs.getDate("dt_vencimento").toLocalDate());
                fatura.setDataBaixa(rs.getDate("dt_baixa").toLocalDate());
                fatura.setParcelaDoPlano(rs.getDouble("parcela_do_plano"));
                fatura.setValorPago(rs.getDouble("valor_pago"));
                fatura.setNumeroFatura(rs.getInt("numero_fatura"));

            }

            fatura.setDataBaixa(dataBaixa);
            fatura.setValorPago(valorBaixa);

            return fatura;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(Fatura fatura) {
        listaFaturas.remove(fatura);
    }





}
