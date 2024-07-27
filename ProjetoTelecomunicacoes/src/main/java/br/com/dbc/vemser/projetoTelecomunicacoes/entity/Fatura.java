package br.com.dbc.vemser.projetoTelecomunicacoes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_fatura")
public class Fatura {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fatura")
    @SequenceGenerator(name = "seq_fatura", sequenceName = "seq_fatura", allocationSize = 1)
    @Column(name = "id_fatura")
    private Integer idFatura;

//    @Column(name = "id_cliente")
//    private Integer idCliente;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;

}

