package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.Fatura;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.entity.planos.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class PessoaRepository {
    private static List<Pessoa> listaPessoas = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();
    private final FaturaRepository faturaRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //18/10/2020

    public PessoaRepository(FaturaRepository faturaRepository) {
        this.faturaRepository = faturaRepository;

        listaPessoas.add(new Pessoa(COUNTER.incrementAndGet() /*1*/, "Maicon Gerardi", LocalDate.parse("10/10/1990", formatter), "12345678910", "juazin1@gmail.com", 1234567891, TipoDePlano.BASICO, true));
        listaPessoas.add(new Pessoa(COUNTER.incrementAndGet() /*2*/, "Charles Pereira", LocalDate.parse("08/05/1985", formatter), "12345678911", "juazin2@gmail.com", 1234567891, TipoDePlano.BASICO, true));
        listaPessoas.add(new Pessoa(COUNTER.incrementAndGet() /*3*/, "Marina Oliveira", LocalDate.parse("30/03/1970", formatter), "12345678912", "juazin3@gmail.com", 1234567891, TipoDePlano.BASICO, true));
        listaPessoas.add(new Pessoa(COUNTER.incrementAndGet() /*4*/, "Rafael Lazzari", LocalDate.parse("01/07/1990", formatter), "12345678916", "juazin4@gmail.com", 1234567891, TipoDePlano.BASICO, true));
        listaPessoas.add(new Pessoa(COUNTER.incrementAndGet() /*5*/, "Ana", LocalDate.parse("01/07/1990", formatter), "12345678917", "juazin5@gmail.com", 1234567891, TipoDePlano.BASICO, true));
    }

    public List<Pessoa> list() {
        return listaPessoas;
    }

    public List<Pessoa> listByName(String nome) {
        return listaPessoas.stream()
                .filter(pessoa -> pessoa.getNome().toUpperCase().contains(nome.toUpperCase()))
                .collect(Collectors.toList());
    }

    public Pessoa create(Pessoa pessoa) {
        log.debug("Entrando na PessoaRepository");
        pessoa.setIdPessoa(COUNTER.incrementAndGet());
        Plano plano = null;
        switch (pessoa.getTipoDePlano()){
            case BASICO:
                plano = new PlanoBasico();
                break;
            case MEDIUM:
                plano = new PlanoMedium();
                break;
            case PREMIUM:
                plano = new PlanoPremium();
                break;
        }
        LocalDate dataAtual = LocalDate.now();
        for (int i = 0; i < 12; i++) {
            LocalDate dataVencimento = dataAtual.plusMonths((i + 1));
            Fatura fatura = new Fatura(faturaRepository.getIdFatura(), pessoa.getIdPessoa(), dataVencimento, null, plano.getValor(), 0, (i+1));
            faturaRepository.create(fatura);
        }
        listaPessoas.add(pessoa);
        return pessoa;
    }

    public Pessoa update(Integer id, Pessoa pessoaAtualizar, Pessoa pessoaRecuperada) {
        log.debug("Entrando na PessoaRepository");
        pessoaRecuperada.setCpf(pessoaAtualizar.getCpf());
        pessoaRecuperada.setNome(pessoaAtualizar.getNome());
        pessoaRecuperada.setDataNascimento(pessoaAtualizar.getDataNascimento());
        return pessoaRecuperada;
    }

    public void delete(Pessoa pessoa) {
        log.debug("Entrando na PessoaRepository");
        pessoa.setStatus(false);
    }

}