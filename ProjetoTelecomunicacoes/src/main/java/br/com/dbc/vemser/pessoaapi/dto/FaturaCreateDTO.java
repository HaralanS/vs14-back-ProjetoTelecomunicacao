package br.com.dbc.vemser.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaturaCreateDTO {

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
