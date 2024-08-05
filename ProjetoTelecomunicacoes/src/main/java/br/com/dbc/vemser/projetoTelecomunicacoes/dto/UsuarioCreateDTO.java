package br.com.dbc.vemser.projetoTelecomunicacoes.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioCreateDTO {

    private String login;
    private String senha;
}
