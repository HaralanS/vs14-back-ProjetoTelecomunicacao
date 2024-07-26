package br.com.dbc.vemser.projetoTelecomunicacoes.dto;

import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Fatura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteComFaturaDTO extends ClienteCreateDTO {

    private Integer idPessoa;

    private Set<Fatura> faturas;

}
