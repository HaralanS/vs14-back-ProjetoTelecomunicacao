package br.com.dbc.vemser.projetoTelecomunicacoes.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertieReader {

    @Value("${ambiente}")
    private String ambiente;
    public String getAmbiente() {
        return ambiente;
    }

}
