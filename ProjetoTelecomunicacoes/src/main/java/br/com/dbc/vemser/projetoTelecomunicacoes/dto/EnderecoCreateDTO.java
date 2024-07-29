package br.com.dbc.vemser.projetoTelecomunicacoes.dto;

import br.com.dbc.vemser.projetoTelecomunicacoes.entity.TipoEndereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoCreateDTO {

    @Schema(description = "Id da pessoa.", example = "1")
    private Integer idPessoa;

    @Schema(description = "Tiop RESIDENCIAL ou COMERCIAL.", example = "COMERCIAL")
    @NotNull
    private TipoEndereco tipo;

    @Schema(description = "Logradouro, ou rua/condominio que a pessoa mora.", example = "Rua José Mourinho")
    @NotEmpty
    @Size(max = 250)
    private String logradouro;

    @Schema(description = "Logradouro, ou rua/condominio que a pessoa mora.", example = "Rua José Mourinho")
    @NotNull
    private Integer numero;

    @Schema(description = "Proximidade de algum estabelecimento por exemplo.", example = "Próximo ao colégio municipal")
    private String complemento;

    @Schema(description = "CEP sem caracteres especiais.", example = "58441000")
    @NotEmpty
    @NotNull
    @Size(max = 8)
    private String cep;

    @Schema(description = "Cidade em que a pessoa mora.", example = "Porto Alegre")
    @NotEmpty
    @NotNull
    @Size(max = 250)
    private String cidade;

    @Schema(description = "Estado em que a pessoa mora.", example = "Rio Grande do Sul")
    @NotNull
    private String estado;

    @Schema(description = "Pais em que a pessoa mora.", example = "Brasil")
    @NotNull
    private String pais;

}
