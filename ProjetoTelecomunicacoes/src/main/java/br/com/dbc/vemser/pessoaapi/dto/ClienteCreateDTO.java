package br.com.dbc.vemser.pessoaapi.dto;

import br.com.dbc.vemser.pessoaapi.entity.planos.TipoDePlano;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCreateDTO {

    @Schema(description = "Nome da Pessoa", example = "Rafael Lazzari")
    @NotBlank
    private String nome;

    @Schema(description = "Data de nascimento da Pessoa", example = "1990-01-01")
    @Past
    @NotNull
    private LocalDate dataNascimento;

    @Schema(description = "CPF de uma Pessoa", example = "123456789101")
    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @Schema(description = "E-mail de uma Pessoa", example = "pessoa.contato@gmail.com")
    @Email
    private String email;

    @Schema(description = "Numero de telefone com ddd", example = "47999556687")
    @NotNull
    private Long numeroTelefone;

    @Schema(description = "Status do cliente", example = "true")
    @NotNull
    private boolean status;

    @Schema(description = "Tipo de plano de 1 a 3", example = "2")
    @NotNull
    private TipoDePlano tipoDePlano;


}
