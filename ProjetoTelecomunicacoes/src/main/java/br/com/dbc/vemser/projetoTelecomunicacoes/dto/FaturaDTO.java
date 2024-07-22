package br.com.dbc.vemser.projetoTelecomunicacoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaturaDTO extends FaturaCreateDTO{

    private Integer idFatura;
}
