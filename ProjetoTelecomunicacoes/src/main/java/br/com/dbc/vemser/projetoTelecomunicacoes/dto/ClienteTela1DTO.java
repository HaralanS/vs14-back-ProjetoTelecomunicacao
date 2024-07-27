package br.com.dbc.vemser.projetoTelecomunicacoes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteTela1DTO{

    private String nome;

    private LocalDate dataNascimento;

    private String cpf;

    private String email;

    private Long numeroTelefone;

}
