package br.com.dbc.vemser.projetoTelecomunicacoes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class FaturaCreateDTO {

    public FaturaCreateDTO(Integer idCliente, LocalDate dataVencimento, LocalDate dataBaixa, double parcelaDoPlano, double valorPago, Integer numeroFatura) {
        this.idCliente = idCliente;
        this.dataVencimento = dataVencimento;
        this.dataBaixa = dataBaixa;
        this.parcelaDoPlano = parcelaDoPlano;
        this.valorPago = valorPago;
        this.numeroFatura = numeroFatura;
    }

    @Schema(description = "Id do cliente", example = "2")
    private Integer idCliente;

    @Schema(description = "Data de vencimento da fatura", example = "2025-01-01")
    @NotNull
    private LocalDate dataVencimento;

    @Schema(description = "Data de pagamento da fatura", example = "2025-01-01")
    private LocalDate dataBaixa;

    @Schema(description = "Valor da fatura", example = "99,90")
    @NotNull
    private double parcelaDoPlano;

    @Schema(description = "Valor da fatura", example = "99,90")
    private double valorPago;

    @Schema(description = "Numero da fatura", example = "2")
    @NotNull
    private Integer numeroFatura;



}
