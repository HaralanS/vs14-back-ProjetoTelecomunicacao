package br.com.dbc.vemser.projetoTelecomunicacoes.entity.planos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Plano {

    private TipoDePlano tipoDePlano;
    private double valor;
    private List<String> beneficios;

    public Plano(){};


}
