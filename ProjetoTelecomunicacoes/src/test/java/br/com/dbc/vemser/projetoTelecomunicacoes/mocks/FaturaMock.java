package br.com.dbc.vemser.projetoTelecomunicacoes.mocks;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.FaturaCreateDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.FaturaDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Fatura;
import java.time.LocalDate;

public class FaturaMock {

    public Fatura retornarEntidadeFatura(Integer numero) {
        Fatura  fatura = new Fatura();
        fatura.setIdFatura(numero);
        fatura.setDataVencimento(LocalDate.parse("2000-01-0"+numero));
        fatura.setDataBaixa(null);
        fatura.setParcelaDoPlano(90);
        fatura.setValorPago(180);
        fatura.setNumeroFatura(numero);

        return fatura;
    }

    public FaturaDTO retornarEntidadeFaturaDTO(Integer numero) {
        FaturaDTO  faturaDTO = new FaturaDTO();
        faturaDTO.setIdFatura(numero);
        faturaDTO.setDataVencimento(LocalDate.parse("2000-01-0"+numero));
        faturaDTO.setDataBaixa(null);
        faturaDTO.setParcelaDoPlano(90);
        faturaDTO.setValorPago(180);
        faturaDTO.setNumeroFatura(numero);

        return faturaDTO;
    }

    public FaturaCreateDTO retornarEntidadeFaturaCreateDTO(Integer numero) {
        FaturaCreateDTO  faturaCreateDTO = new FaturaCreateDTO();
        faturaCreateDTO.setDataVencimento(LocalDate.parse("2000-01-0"+numero));
        faturaCreateDTO.setDataBaixa(null);
        faturaCreateDTO.setParcelaDoPlano(90);
        faturaCreateDTO.setValorPago(180);
        faturaCreateDTO.setNumeroFatura(numero);

        return faturaCreateDTO;
    }
}
