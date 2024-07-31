package br.com.dbc.vemser.projetoTelecomunicacoes.dto;

import br.com.dbc.vemser.projetoTelecomunicacoes.entity.planos.TipoDePlano;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCreateDTO {

    @Schema(description = "Nome da Pessoa", example = "Joao da Silva")
    @NotBlank
    private String nome;

    @Schema(description = "Data de nascimento da Pessoa", example = "1990-01-01")
    @Past
    @NotNull
    private LocalDate dataNascimento;

    @Schema(description = "CPF de uma Pessoa", example = "12345678911")
    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @Schema(description = "E-mail de uma Pessoa", example = "pessoa.contato@gmail.com")
    @Email
    private String email;

    @Schema(description = "Numero de telefone", example = "99556687")
    @NotNull
    private Long numeroTelefone;

    @Schema(description = "Tipo de plano de 0 a 2", example = "2")
    @NotNull
    private TipoDePlano tipoDePlano;

    @Schema(description = "Status do cliente", example = "true")
    @NotNull
    private boolean status;


    @Schema(description = "Login", example = "usuario")
    @NotBlank
    private String login;

    @Schema(description = "Senha", example = "usuario123")
    @NotBlank
    private String senha;



}
