package br.com.dbc.vemser.projetoTelecomunicacoes.service;


import br.com.dbc.vemser.projetoTelecomunicacoes.dto.FaturaCreateDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.FaturaDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Fatura;
import br.com.dbc.vemser.projetoTelecomunicacoes.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.projetoTelecomunicacoes.repository.ClienteRepository;
import br.com.dbc.vemser.projetoTelecomunicacoes.repository.FaturaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FaturaService {

    private final FaturaRepository faturaRepository;
    private final ObjectMapper objectMapper;
    private final ClienteRepository clienteRepository;

    public FaturaService(FaturaRepository faturaRepository, ObjectMapper objectMapper, ClienteRepository clienteRepository) {
        this.faturaRepository = faturaRepository;
        this.objectMapper = objectMapper;
        this.clienteRepository = clienteRepository;
    }

    public List<FaturaDTO> list(){
        List<FaturaDTO> list = faturaRepository.list().stream()
                .map(fatura -> objectMapper.convertValue(fatura, FaturaDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    public List<FaturaDTO> listByClient(Integer idCliente) throws Exception {
        getIdCliente(idCliente);

        return  faturaRepository.listByClient(idCliente).stream()
                .map(fatura -> objectMapper.convertValue(fatura, FaturaDTO.class))
                .collect(Collectors.toList());
    }



    public void pagarFatura(Integer idCliente, Integer numeroFatura, double valorBaixa, LocalDate dataBaixa) throws Exception {
        FaturaDTO faturaDTO = objectMapper.convertValue(faturaRepository.pagarFatura(idCliente, numeroFatura, valorBaixa, dataBaixa), FaturaDTO.class);

    }

    public FaturaDTO create(FaturaCreateDTO faturaCreateDTO) throws Exception {

        Fatura fatura = objectMapper.convertValue(faturaCreateDTO, Fatura.class);
        faturaRepository.create(fatura);
        FaturaDTO faturaDTO = objectMapper.convertValue(fatura, FaturaDTO.class);
        return faturaDTO;

    }

//    public FaturaDTO findByIdDTO(Integer idFatura) throws Exception {
//        Fatura faturaRecuperada = faturaRepository.list().stream().filter(fatura -> fatura.getIdFatura()
//                .equals(idFatura)).findFirst().orElseThrow(() -> new RegraDeNegocioException("Fatura não encontrada!"));
//        FaturaDTO faturaDTO = objectMapper.convertValue(faturaRecuperada, FaturaDTO.class);
//        return faturaDTO;
//    }

    private Fatura getFatura(Integer idFatura) throws Exception {
        Fatura faturaRecuperada = faturaRepository.list().stream().filter(fatura -> fatura.getIdFatura()
                .equals(idFatura)).findFirst().orElseThrow(() -> new RegraDeNegocioException("Fatura não encontrada!"));

        return faturaRecuperada;
    }

    public void delete(Integer id) throws Exception {
        Fatura fatura = getFatura(id);
        faturaRepository.delete(fatura);
    }

    private Integer getIdCliente(Integer id) throws Exception {
        return clienteRepository.getAllClientes().stream()
                .filter(cliente -> cliente.getIdPessoa().equals(id))
                .findFirst().orElseThrow(() -> new RuntimeException("Cliente não encontrado!")).getIdPessoa();

    }

}