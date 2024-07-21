package br.com.dbc.vemser.pessoaapi.entity;

import br.com.dbc.vemser.pessoaapi.entity.planos.Plano;
import br.com.dbc.vemser.pessoaapi.entity.planos.TipoDePlano;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Pessoa {

    private Integer idPessoa;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String email;
    private Integer numeroTelefone;
    private TipoDePlano tipoDePlano;
    private boolean status;

}
