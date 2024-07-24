package br.com.dbc.vemser.projetoTelecomunicacoes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fatura {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fatura")
    @SequenceGenerator(name = "seq_fatura", sequenceName = "seq_fatura", allocationSize = 1)
    @Column(name = "id_fatura")
    private Integer idFatura;

    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "dt_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "dt_baixa")
    private LocalDate dataBaixa;

    @Column(name = "parcela")
    private double parcelaDoPlano;

    @Column(name = "valor_pago")
    private double valorPago;

    @Column(name = "numero_fatura")
    private Integer numeroFatura;

}

