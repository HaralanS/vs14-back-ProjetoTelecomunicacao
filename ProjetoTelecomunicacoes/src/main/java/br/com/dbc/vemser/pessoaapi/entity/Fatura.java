package br.com.dbc.vemser.pessoaapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Fatura {

    private Integer idFatura;
    private Integer idCliente;
    private LocalDate dataVencimento;
    private LocalDate dataBaixa;
    private double parcelaDoPlano;
    private double valorPago;
    private Integer numeroFatura;
}

