package br.com.dbc.vemser.projetoTelecomunicacoes.mocks;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.ClienteCreateDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.ClienteDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Cliente;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Endereco;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Fatura;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.UsuarioEntity;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.planos.TipoDePlano;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

public class ClienteMock {



    public Cliente retronaClienteEntidade(Integer numero){
         Cliente cliente = new Cliente();

         cliente.setIdCliente(numero);
         cliente.setNome("nome" + numero);
         cliente.setDataNascimento(LocalDate.parse("2000-01-0"+numero));
         cliente.setCpf("1234567890"+numero);
         cliente.setEmail("teste@teste.com");
         cliente.setNumeroTelefone(9999999999L);
         cliente.setTipoDePlano(TipoDePlano.ofTipo(1));
         cliente.setStatus(true);
        return cliente;
    }
    public ClienteDTO retornaClienteDTO(Integer numero){
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setIdCliente(numero);
        clienteDTO.setNome("nome" + numero);
        clienteDTO.setDataNascimento(LocalDate.parse("2000-01-0"+numero));
        clienteDTO.setCpf("1234567890"+numero);
        clienteDTO.setEmail("teste@teste.com");
        clienteDTO.setNumeroTelefone(9999999999L);
        clienteDTO.setTipoDePlano(TipoDePlano.ofTipo(1));
        clienteDTO.setStatus(true);
        return clienteDTO;
    }
    public ClienteCreateDTO retornaClienteCreateDTO(Integer numero){
        ClienteCreateDTO clienteCreateDTO = new ClienteDTO();

        clienteCreateDTO.setNome("nome" + numero);
        clienteCreateDTO.setDataNascimento(LocalDate.parse("2000-01-0"+numero));
        clienteCreateDTO.setCpf("1234567890"+numero);
        clienteCreateDTO.setEmail("teste@teste.com");
        clienteCreateDTO.setNumeroTelefone(9999999999L);
        clienteCreateDTO.setTipoDePlano(TipoDePlano.ofTipo(1));
        clienteCreateDTO.setStatus(true);
        return clienteCreateDTO;
    }
}