package br.com.dbc.vemser.projetoTelecomunicacoes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDTO {

    @Schema(description = "Numero da fatura", example = "2")
    @NotNull
    private Integer numeroFatura;

    @Schema(description = "Valor do pagamento", example = "59.9")
    @NotNull
    private double valorPago;

    @Schema(description = "Data de pagamento da fatura", example = "2025-01-01")
    @NotNull
    private LocalDate dataBaixa;


}
