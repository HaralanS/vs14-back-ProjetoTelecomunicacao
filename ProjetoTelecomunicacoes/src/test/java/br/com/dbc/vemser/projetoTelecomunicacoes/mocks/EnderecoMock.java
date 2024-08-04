package br.com.dbc.vemser.projetoTelecomunicacoes.mocks;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Endereco;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.TipoEndereco;

public class EnderecoMock {

    private ClienteMock clienteMock = new ClienteMock();

    public Endereco retornarEntidadeEndereco(Integer numero) {

        Endereco endereco = new Endereco();
        endereco.setIdEndereco(numero);
        endereco.setTipo(TipoEndereco.ofTipo(1));
        endereco.setLogradouro("Endereco " + numero);
        endereco.setNumero(numero);
        endereco.setComplemento("Casa");
        endereco.setCep("5959599");
        endereco.setCidade("Santos");
        endereco.setEstado("São Paulo");
        endereco.setPais("Brasil");
        endereco.setCliente(clienteMock.retronaClienteEntidade(numero));

        return endereco;
    }

    public EnderecoDTO retornarEntidadeEnderecoDTO(Integer numero) {

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setIdEndereco(numero);
        enderecoDTO.setTipo(TipoEndereco.ofTipo(1));
        enderecoDTO.setLogradouro("Endereco " + numero);
        enderecoDTO.setNumero(numero);
        enderecoDTO.setComplemento("Casa");
        enderecoDTO.setCep("5959599");
        enderecoDTO.setCidade("Santos");
        enderecoDTO.setEstado("São Paulo");
        enderecoDTO.setPais("Brasil");

        return enderecoDTO;
    }

    public EnderecoCreateDTO retornarEntidadeEnderecoCreateDTO(Integer numero) {

        EnderecoCreateDTO enderecoCreateDTO = new EnderecoCreateDTO();
        enderecoCreateDTO.setTipo(TipoEndereco.ofTipo(1));
        enderecoCreateDTO.setLogradouro("Endereco " + numero);
        enderecoCreateDTO.setNumero(numero);
        enderecoCreateDTO.setComplemento("Casa");
        enderecoCreateDTO.setCep("5959599");
        enderecoCreateDTO.setCidade("Santos");
        enderecoCreateDTO.setEstado("São Paulo");
        enderecoCreateDTO.setPais("Brasil");

        return enderecoCreateDTO;
    }
}