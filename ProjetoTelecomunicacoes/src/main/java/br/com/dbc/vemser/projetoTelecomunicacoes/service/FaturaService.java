package br.com.dbc.vemser.projetoTelecomunicacoes.service;


import br.com.dbc.vemser.projetoTelecomunicacoes.dto.FaturaCreateDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.FaturaDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.PagamentoDTO;
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

    public List<FaturaDTO> listByIdClient(Integer idCliente) {
        log.debug("Entrando na FaturaService");
        List<FaturaDTO> list = faturaRepository.findAllByIdCliente(idCliente)
                .stream()
                .map(fatura -> objectMapper.convertValue(fatura, FaturaDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    public List<FaturaDTO> payInvoice(Integer id, PagamentoDTO pagamentoDTO) throws Exception {
        log.debug("Entrando na FaturaService");
        List<FaturaDTO> list = listByIdClient(id);
        for (FaturaDTO fatura : list) {
            if (fatura.getNumeroFatura() == pagamentoDTO.getNumeroFatura()) {
                fatura.setDataBaixa(pagamentoDTO.getDataBaixa());
                fatura.setValorPago(pagamentoDTO.getValorPago());
                break;
            }
        }
        faturaRepository.deleteByPessoaId(id);
        for (FaturaDTO fatura : list) {
            create(fatura);
        }
        return list;
    }

    public void create(FaturaDTO dto) {
        Fatura faturaEntity = objectMapper.convertValue(dto, Fatura.class);
        faturaRepository.save(faturaEntity);
    }







}
