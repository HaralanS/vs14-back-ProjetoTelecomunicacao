package br.com.dbc.vemser.projetoTelecomunicacoes.mocks;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.PagamentoDTO;

import java.time.LocalDate;

public class PagamentoMock {
    public PagamentoDTO retornaPagamentoDTO(Integer numero){
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setNumeroFatura(numero);
        pagamentoDTO.setValorPago(100);
        pagamentoDTO.setDataBaixa(LocalDate.EPOCH);
        return pagamentoDTO;
    }
}
