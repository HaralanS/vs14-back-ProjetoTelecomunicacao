package br.com.dbc.vemser.pessoaapi.entity;

import br.com.dbc.vemser.pessoaapi.entity.planos.TipoDePlano;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Cliente {

    private Integer idPessoa;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String email;
    private Long numeroTelefone;
    private TipoDePlano tipoDePlano;
    private Boolean status;

    public Cliente() {

    }
}
