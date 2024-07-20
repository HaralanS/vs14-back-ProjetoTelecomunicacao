package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.entity.TipoContato;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class ContatoRepository {

    private static List<Contato> listaContatos = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();
    private AtomicInteger COUNTER2 = new AtomicInteger();

    public ContatoRepository() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //18/10/2020
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*1*/, COUNTER2.incrementAndGet(), TipoContato.RESIDENCIAL,  "12345678910", "descricao1"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*2*/, COUNTER2.incrementAndGet(), TipoContato.COMERCIAL, "12345678911", "descricao2"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*3*/, 1, TipoContato.RESIDENCIAL, "12345678912", "descricao3"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*4*/, COUNTER2.incrementAndGet(), TipoContato.RESIDENCIAL, "12345678916", "descricao4"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*5*/, 4, TipoContato.COMERCIAL, "12345678917", "descricao5"));
    }

    public List<Contato> list() {
        log.debug("Entrando na ContatoRepository");
        return listaContatos;
    }

    public List<Contato> listById(Integer id) {
        log.debug("Entrando na ContatoRepository");
        return listaContatos.stream()
                .filter(contato -> contato.getIdPessoa().equals(id))
                .collect(Collectors.toList());
    }

    public Contato create(Integer idPessoa, Contato contato) {
        log.debug("Entrando na ContatoRepository");
        contato.setIdPessoa(idPessoa);
        contato.setIdContato(COUNTER.incrementAndGet());
        listaContatos.add(contato);
        return contato;
    }

    public Contato update(Integer id, Contato contatoAtualizar, Contato contatoRecuperado) {
        log.debug("Entrando na ContatoRepository");
        contatoRecuperado.setTipoContato(contatoAtualizar.getTipoContato());
        contatoRecuperado.setNumero(contatoAtualizar.getNumero());
        contatoRecuperado.setDescricao(contatoAtualizar.getDescricao());
        return contatoRecuperado;
    }

    public void delete(Contato contato) {
        log.debug("Entrando na ContatoRepository");
        listaContatos.remove(contato);
    }

}
