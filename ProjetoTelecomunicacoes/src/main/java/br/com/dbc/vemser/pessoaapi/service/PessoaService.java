package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaDTO;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    public PessoaService(PessoaRepository pessoaRepository, ObjectMapper objectMapper, EmailService emailService){
        this.pessoaRepository = pessoaRepository;
        this.objectMapper = objectMapper;
        this.emailService = emailService;
    }

    public List<PessoaDTO> list(){
        List<PessoaDTO> list = pessoaRepository.list()
                .stream()
                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    public List<PessoaDTO> listByName(String nome) {
        List<PessoaDTO> list = pessoaRepository.list()
                .stream()
                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
                .filter(pessoaDTO -> pessoaDTO.getNome().equalsIgnoreCase(nome))
                .collect(Collectors.toList());
        return list;
    }

    public PessoaDTO create(PessoaCreateDTO dto) throws Exception {
        log.debug("Entrando na PessoaService");
        Pessoa pessoaEntity = objectMapper.convertValue(dto, Pessoa.class);
        pessoaRepository.create(pessoaEntity);
        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaEntity, PessoaDTO.class);
//        emailService.sendEmail(pessoaEntity, "cp");
        return pessoaDTO;
    }

    public PessoaDTO update(Integer id, PessoaCreateDTO dto) throws Exception {
        log.debug("Entrando na PessoaService");
        Pessoa pessoaEntity = objectMapper.convertValue(dto, Pessoa.class);
        Pessoa pessoaRecuperada = getPessoa(id);
        pessoaRepository.update(id, pessoaEntity, pessoaRecuperada);
        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaEntity, PessoaDTO.class);
        return pessoaDTO;
    }

    public void delete(Integer id) throws Exception {
        log.debug("Entrando na PessoaService");
        Pessoa pessoaRecuperada = getPessoa(id);
        pessoaRepository.delete(pessoaRecuperada);
    }

    public Pessoa getPessoa(Integer id) throws Exception {
        Pessoa pessoaRecuperada = pessoaRepository.list().stream()
                .filter(pessoa -> pessoa.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada!"));
        return pessoaRecuperada;
    }

}
