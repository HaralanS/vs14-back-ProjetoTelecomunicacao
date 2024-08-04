package br.com.dbc.vemser.projetoTelecomunicacoes.mocks;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Endereco;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.TipoEndereco;

public class EnderecoMock {

    public Endereco retornarEntidadeEndereco(Integer numero) {

        Endereco endereco = new Endereco();
        endereco.setIdEndereco(numero);
        endereco.setTipo(TipoEndereco.ofTipo(1));
        endereco.setLogradouro("Endereco " + numero);
        endereco.setNumero(numero);
        endereco.setComplemento("Casa"+ numero);
        endereco.setCep("5959599"+ numero);
        endereco.setCidade("Santos"+ numero);
        endereco.setEstado("São Paulo"+ numero);
        endereco.setPais("Brasil"+ numero);

        return endereco;
    }

    public EnderecoDTO retornarEntidadeEnderecoDTO(Integer numero) {

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setIdPessoa(numero);
        enderecoDTO.setIdEndereco(numero);
        enderecoDTO.setTipo(TipoEndereco.ofTipo(1));
        enderecoDTO.setLogradouro("Endereco " + numero);
        enderecoDTO.setNumero(numero);
        enderecoDTO.setComplemento("Casa" +numero);
        enderecoDTO.setCep("5959599"+numero);
        enderecoDTO.setCidade("Santos"+numero);
        enderecoDTO.setEstado("São Paulo"+numero);
        enderecoDTO.setPais("Brasil"+numero);

        return enderecoDTO;
    }

    public EnderecoCreateDTO retornarEntidadeEnderecoCreateDTO(Integer numero) {

        EnderecoCreateDTO enderecoCreateDTO = new EnderecoCreateDTO();
        enderecoCreateDTO.setIdPessoa(numero);
        enderecoCreateDTO.setTipo(TipoEndereco.ofTipo(1));
        enderecoCreateDTO.setLogradouro("Endereco " + numero);
        enderecoCreateDTO.setNumero(numero);
        enderecoCreateDTO.setComplemento("Casa"+numero);
        enderecoCreateDTO.setCep("5959599"+numero);
        enderecoCreateDTO.setCidade("Santos"+numero);
        enderecoCreateDTO.setEstado("São Paulo"+numero);
        enderecoCreateDTO.setPais("Brasil"+numero);

        return enderecoCreateDTO;
    }
}