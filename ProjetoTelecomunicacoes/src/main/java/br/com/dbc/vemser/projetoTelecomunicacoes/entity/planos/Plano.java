package br.com.dbc.vemser.projetoTelecomunicacoes.entity.planos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Plano {

    private TipoDePlano tipoDePlano;
    private double valor;
    private List<String> beneficios;

    public Plano(){};
}
