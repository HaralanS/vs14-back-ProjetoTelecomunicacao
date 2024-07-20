package br.com.dbc.vemser.pessoaapi.entity.planos;

import br.com.dbc.vemser.pessoaapi.entity.TipoEndereco;

import java.util.Arrays;

public enum TipoDePlano {
    BASICO(1),
    MEDIUM(2),
    PREMIUM(3);

    private Integer tipo;

    TipoDePlano(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getTipo() {
        return tipo;
    }

    public static TipoDePlano ofTipo(Integer tipo){
        return Arrays.stream(TipoDePlano.values())
                .filter(tp -> tp.getTipo().equals(tipo))
                .findFirst()
                .get();
    }
}
