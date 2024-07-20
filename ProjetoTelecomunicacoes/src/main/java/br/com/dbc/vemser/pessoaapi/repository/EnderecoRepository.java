package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import br.com.dbc.vemser.pessoaapi.entity.TipoEndereco;

@Slf4j
@Repository
public class EnderecoRepository {

    private static List<Endereco> listaEnderecos = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();
    private AtomicInteger COUNTER2 = new AtomicInteger();

    EnderecoRepository() {
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), COUNTER2.incrementAndGet(), TipoEndereco.RESIDENCIAL, "Rua A", 123, "Apto 1", "12345-678", "Cidade A", "Estado A", "Pais A"));
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), COUNTER2.incrementAndGet(), TipoEndereco.COMERCIAL, "Avenida B", 456, "Sala 101", "23456-789", "Cidade B", "Estado B", "Pais B"));
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), 1, TipoEndereco.RESIDENCIAL, "Rua C", 789, null, "34567-890", "Cidade C", "Estado C", "Pais C"));
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), COUNTER2.incrementAndGet(), TipoEndereco.RESIDENCIAL, "Rua D", 321, "Casa", "45678-901", "Cidade D", "Estado D", "Pais D"));
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), 4, TipoEndereco.COMERCIAL, "Avenida E", 654, "Loja 1", "56789-012", "Cidade E", "Estado E", "Pais E"));
    }

    public List<Endereco> list() {
        log.debug("Entrando na EnderecoRepository");
        return listaEnderecos;
    }

    public List<Endereco> listById(Integer id) {
        log.debug("Entrando na EnderecoRepository");
        return listaEnderecos.stream()
                .filter(endereco -> endereco.getIdEndereco().equals(id))
                .collect(Collectors.toList());
    }

    public List<Endereco> listByIdPessoa(Integer id) {
        log.debug("Entrando na EnderecoRepository");
        return listaEnderecos.stream()
                .filter(endereco -> endereco.getIdPessoa().equals(id))
                .collect(Collectors.toList());
    }

    public Endereco create(Integer id, Endereco endereco) {
        log.debug("Entrando na EnderecoRepository");
        endereco.setIdEndereco(COUNTER.incrementAndGet());
        endereco.setIdPessoa(id);
        listaEnderecos.add(endereco);
        return endereco;
    }

    public Endereco update(Integer id, Endereco enderecoAtualizar, Endereco enderecoRecuperado) {
        log.debug("Entrando na EnderecoRepository");
        enderecoRecuperado.setTipo(enderecoAtualizar.getTipo());
        enderecoRecuperado.setLogradouro(enderecoAtualizar.getLogradouro());
        enderecoRecuperado.setNumero(enderecoAtualizar.getNumero());
        enderecoRecuperado.setComplemento(enderecoAtualizar.getComplemento());
        enderecoRecuperado.setCep(enderecoAtualizar.getCep());
        enderecoRecuperado.setCidade(enderecoAtualizar.getCidade());
        enderecoRecuperado.setEstado(enderecoAtualizar.getEstado());
        enderecoRecuperado.setPais(enderecoAtualizar.getPais());
        return enderecoRecuperado;
    }

    public void delete(Endereco endereco) {
        log.debug("Entrando na EnderecoRepository");
        listaEnderecos.remove(endereco);
    }
}
