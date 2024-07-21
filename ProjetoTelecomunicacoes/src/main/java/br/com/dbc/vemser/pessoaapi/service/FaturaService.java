package br.com.dbc.vemser.pessoaapi.service;


import br.com.dbc.vemser.pessoaapi.dto.FaturaCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.FaturaDTO;
import br.com.dbc.vemser.pessoaapi.entity.Fatura;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.FaturaRepository;
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

    public FaturaService(FaturaRepository faturaRepository, ObjectMapper objectMapper) {
        this.faturaRepository = faturaRepository;
        this.objectMapper = objectMapper;
    }

    public List<FaturaDTO> list(){
        List<FaturaDTO> list = faturaRepository.list().stream()
                .map(fatura -> objectMapper.convertValue(fatura, FaturaDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    public List<FaturaDTO> listByClient(Integer idCliente){
        List<Fatura> list = faturaRepository.listByClient(idCliente).stream()
                .filter(fatura -> fatura.getIdCliente().equals(idCliente))
                .collect(Collectors.toList());
        List<FaturaDTO> faturasDto = list.stream()
                .map(fatura -> objectMapper.convertValue(fatura, FaturaDTO.class))
                .collect(Collectors.toList());


        return faturasDto;
    }



    public FaturaDTO pagarFatura(Integer idCliente, Integer numeroFatura, double valorBaixa, LocalDate dataBaixa) throws Exception {
        FaturaDTO faturaDTO = objectMapper.convertValue(faturaRepository.pagarFatura(idCliente, numeroFatura, valorBaixa, dataBaixa), FaturaDTO.class);
        return faturaDTO;
    }

    public FaturaDTO create(FaturaCreateDTO faturaCreateDTO) throws Exception {
        Fatura fatura = objectMapper.convertValue(faturaCreateDTO, Fatura.class);
        faturaRepository.create(fatura);
        FaturaDTO faturaDTO = objectMapper.convertValue(fatura, FaturaDTO.class);
        return faturaDTO;
    }

    public FaturaDTO findByIdDTO(Integer idFatura) throws Exception {
        Fatura faturaRecuperada = faturaRepository.list().stream().filter(fatura -> fatura.getIdFatura()
                .equals(idFatura)).findFirst().orElseThrow(() -> new RegraDeNegocioException("Fatura não encontrada!"));
        FaturaDTO faturaDTO = objectMapper.convertValue(faturaRecuperada, FaturaDTO.class);
        return faturaDTO;
    }

    private Fatura getFatura(Integer idFatura) throws Exception {
        Fatura faturaRecuperada = faturaRepository.list().stream().filter(fatura -> fatura.getIdFatura()
                .equals(idFatura)).findFirst().orElseThrow(() -> new RegraDeNegocioException("Fatura não encontrada!"));

        return faturaRecuperada;
    }

    public void delete(Integer id) throws Exception {
        Fatura fatura = getFatura(id);
        faturaRepository.delete(fatura);
    }



}
