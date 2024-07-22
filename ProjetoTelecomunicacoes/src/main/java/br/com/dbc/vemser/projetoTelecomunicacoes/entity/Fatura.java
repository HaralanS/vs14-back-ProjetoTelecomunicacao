package br.com.dbc.vemser.projetoTelecomunicacoes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fatura {

    public Fatura(Integer idCliente, LocalDate dataVencimento, LocalDate dataBaixa, double parcelaDoPlano, double valorPago, Integer numeroFatura) {
        this.idCliente = idCliente;
        this.dataVencimento = dataVencimento;
        this.dataBaixa = dataBaixa;
        this.parcelaDoPlano = parcelaDoPlano;
        this.valorPago = valorPago;
        this.numeroFatura = numeroFatura;
    }

    private Integer idFatura;
    private Integer idCliente;
    private LocalDate dataVencimento;
    private LocalDate dataBaixa;
    private double parcelaDoPlano;
    private double valorPago;
    private Integer numeroFatura;
}

