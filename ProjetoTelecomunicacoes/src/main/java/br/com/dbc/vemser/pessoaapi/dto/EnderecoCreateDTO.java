package br.com.dbc.vemser.pessoaapi.dto;

import br.com.dbc.vemser.pessoaapi.entity.TipoEndereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoCreateDTO {

    private Integer idPessoa;

    @NotNull
    private TipoEndereco tipo;

    @NotEmpty
    @Size(max = 250)
    private String logradouro;

    @NotNull
    private Integer numero;

    private String complemento;

    @NotEmpty
    @NotNull
    @Size(max = 8)
    private String cep;

    @NotEmpty
    @NotNull
    @Size(max = 250)
    private String cidade;

    @NotNull
    private String estado;

    @NotNull
    private String pais;

}
