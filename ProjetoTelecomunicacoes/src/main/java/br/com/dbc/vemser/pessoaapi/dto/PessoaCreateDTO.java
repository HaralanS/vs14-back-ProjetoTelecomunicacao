package br.com.dbc.vemser.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaCreateDTO {

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

}
