package br.com.dbc.vemser.projetoTelecomunicacoes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FaturaDTO extends FaturaCreateDTO{

    @Schema(description = "Id da fatura", example = "1")
    private Integer idFatura;
}
