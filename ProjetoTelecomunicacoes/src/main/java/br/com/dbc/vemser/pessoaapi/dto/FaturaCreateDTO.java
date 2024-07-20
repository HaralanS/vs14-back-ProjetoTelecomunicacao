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

    @Schema(description = "Valor da fatura", example = "99,90")
    @NotNull
    private double valor;

    @Schema(description = "Numero de telefone com ddd", example = "47999556687")
    @NotNull
    @Size(min = 10, max = 11)
    private Integer numeroTelefone;

}
