package br.com.dbc.vemser.projetoTelecomunicacoes.dto;

import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Fatura;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteComFaturaDTO extends ClienteCreateDTO {

    private Integer idPessoa;

    private Set<Fatura> faturas;

}
