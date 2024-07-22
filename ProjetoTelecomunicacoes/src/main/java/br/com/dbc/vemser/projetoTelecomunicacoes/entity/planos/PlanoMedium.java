package br.com.dbc.vemser.projetoTelecomunicacoes.entity.planos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class PlanoMedium extends Plano {

    public PlanoMedium(TipoDePlano tipoDePlano, double valor, List<String> beneficios) {
        super(tipoDePlano, valor, beneficios);
        tipoDePlano = TipoDePlano.MEDIUM;
        valor = 99.9;
        beneficios.add("Personalização e Recomendação");
        beneficios.add("Acesso limitado");
        beneficios.add("Sem Anúncios");
        beneficios.add("Definicao 4k");
        beneficios.add("");
    }
}
