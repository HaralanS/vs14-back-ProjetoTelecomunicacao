package br.com.dbc.vemser.projetoTelecomunicacoes.entity.planos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlanoBasico extends Plano{

    public PlanoBasico(TipoDePlano tipoDePlano, double valor, List<String> beneficios) {
        super(tipoDePlano, valor, beneficios);
        tipoDePlano = TipoDePlano.BASICO;
        valor = 59.9;
        beneficios.add("Personalização e Recomendação");
        beneficios.add("Acesso limitado");
        beneficios.add("Sem Anúncios");
        beneficios.add("");
        beneficios.add("");
    }
}
