package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.Fatura;
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
public class FaturaRepository {

    private static List<Fatura> listaFaturas = new ArrayList<>();
    private AtomicInteger COUNTERFATURA = new AtomicInteger();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //18/10/2020

    public FaturaRepository(){

        listaFaturas.add(new Fatura(COUNTERFATURA.incrementAndGet(), 1, LocalDate.parse("10/10/2024", formatter), null, 59.9, 0, null));
        listaFaturas.add(new Fatura(COUNTERFATURA.incrementAndGet(), 2, LocalDate.parse("10/11/2024", formatter), null, 99.9, 0, null));
    }

    public List<Fatura> list() {
        return listaFaturas;
    }
    public List<Fatura> listByClient(Integer idCliente) {
        return listaFaturas.stream().filter(fatura -> fatura.getIdCliente() == idCliente).collect(Collectors.toList());
    }

    public int getIdFatura(){
        return COUNTERFATURA.incrementAndGet();
    }



        /*
            public List<Fatura> findById(Integer idFatura) {
        return listaFaturas.stream().filter(fatura -> fatura.getIdFatura() == idFatura).collect(Collectors.toList());
    }

        public Pessoa getPessoa(Integer id) throws Exception {
        Pessoa pessoaRecuperada = pessoaRepository.list().stream()
                .filter(pessoa -> pessoa.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada!"));
        return pessoaRecuperada;
    }
     */

    public Fatura create(Fatura fatura) {
        fatura.setIdFatura(COUNTERFATURA.incrementAndGet());
        listaFaturas.add(fatura);
        return fatura;
    }

    public Fatura pagarFatura(Integer idCliente, Integer numeroFatura, double valorBaixa, LocalDate dataBaixa) {
        List<Fatura> listaProvisoria = listaFaturas.stream().filter(fatura -> fatura.getIdCliente()
                .equals(idCliente)).collect(Collectors.toList());

        Fatura faturaAtualizar = listaProvisoria.stream().filter(fatura -> fatura.getNumeroFatura()
                .equals(numeroFatura)).collect(Collectors.toList()).get(0);

        faturaAtualizar.setDataBaixa(dataBaixa);
        faturaAtualizar.setValorPago(valorBaixa);

        return faturaAtualizar;
    }

    public void delete(Fatura fatura) {
        listaFaturas.remove(fatura);
    }





}
