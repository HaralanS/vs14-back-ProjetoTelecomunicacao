package br.com.dbc.vemser.pessoaapi.entity.planos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class Plano {

    private TipoDePlano tipoDePlano;
    private double valor;
    private List<String> beneficios;

    public Plano(){};
}
