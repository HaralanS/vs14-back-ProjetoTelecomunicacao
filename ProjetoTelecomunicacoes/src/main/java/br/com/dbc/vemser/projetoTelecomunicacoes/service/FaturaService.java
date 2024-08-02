package br.com.dbc.vemser.projetoTelecomunicacoes.service;


import br.com.dbc.vemser.projetoTelecomunicacoes.dto.FaturaCreateDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.FaturaDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.PagamentoDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.PageDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Fatura;
import br.com.dbc.vemser.projetoTelecomunicacoes.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.projetoTelecomunicacoes.repository.ClienteRepository;
import br.com.dbc.vemser.projetoTelecomunicacoes.repository.FaturaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FaturaService {

    private final FaturaRepository faturaRepository;
    private final ObjectMapper objectMapper;
    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;



    public FaturaService(FaturaRepository faturaRepository, ObjectMapper objectMapper, ClienteRepository clienteRepository, ClienteService clienteService) {
        this.faturaRepository = faturaRepository;
        this.objectMapper = objectMapper;
        this.clienteRepository = clienteRepository;
        this.clienteService = clienteService;
    }

    public List<FaturaDTO> listByIdClient(Integer idCliente) {
        log.debug("Entrando na FaturaService");
        List<FaturaDTO> list = faturaRepository.findFaturaPorIdPessoa(idCliente)
                .stream()
                .map(fatura -> {
                    FaturaDTO faturaDTO = objectMapper.convertValue(fatura, FaturaDTO.class);
                    faturaDTO.setIdCliente(fatura.getCliente().getIdCliente());
                    return faturaDTO;
                })
                .collect(Collectors.toList());
        return list;
    }

    public PageDTO<FaturaDTO> listFaturasPaginacao(Integer pagina, Integer tamanho) throws RegraDeNegocioException {
        log.debug("Entrando na FaturaService");
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("idFatura").ascending());

        Page<Fatura> faturaPage = faturaRepository.findAll(pageable);

        Page<FaturaDTO> faturaDTOPage = faturaPage.map(fatura -> {
           FaturaDTO faturaDTO = objectMapper.convertValue(fatura, FaturaDTO.class);
           faturaDTO.setIdCliente(fatura.getCliente().getIdCliente());
           return faturaDTO;
        });

        if (faturaDTOPage.isEmpty()){
            throw new RegraDeNegocioException("Nenhuma fatura encontrado");
        }


        return new PageDTO<>(
                faturaDTOPage.getTotalElements(),
                faturaDTOPage.getTotalPages(),
                faturaDTOPage.getPageable().getPageNumber(),
                faturaDTOPage.getSize(),
                faturaDTOPage.getContent()
        );
    }

    public FaturaDTO pagarFatura(Integer id, PagamentoDTO pagamentoDTO) throws Exception {
        Fatura fatura = getFatura(id);
        fatura.setValorPago(pagamentoDTO.getValorPago());
        fatura.setDataBaixa(pagamentoDTO.getDataBaixa());
        fatura = faturaRepository.save(fatura);

        return objectMapper.convertValue(fatura, FaturaDTO.class);

    }

    public void create(FaturaDTO dto) {
        Fatura faturaEntity = objectMapper.convertValue(dto, Fatura.class);
        faturaRepository.save(faturaEntity);
    }

    public Fatura getFatura(Integer id) throws Exception {
        return faturaRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("Fatura de id " + id + " nao encontrada"));
    }
}