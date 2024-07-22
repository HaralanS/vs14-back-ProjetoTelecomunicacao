package br.com.dbc.vemser.projetoTelecomunicacoes.entity.planos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class PlanoPremium extends Plano {

    public PlanoPremium(TipoDePlano tipoDePlano, double valor, List<String> beneficios) {
        super(tipoDePlano, valor, beneficios);
        tipoDePlano = TipoDePlano.PREMIUM;
        valor = 59.9;
        beneficios.add("Personalização e Recomendação");
        beneficios.add("Visualização Offline");
        beneficios.add("Sem Anúncios");
        beneficios.add("Definicao 4k");
        beneficios.add("Acesso ilimitado");
    }
}
