package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.ContatoDTO;
import br.com.dbc.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.ContatoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final PessoaService pessoaService;
    private final ObjectMapper objectMapper;

    public ContatoService(ContatoRepository contatoRepository, PessoaService pessoaService, ObjectMapper objectMapper) {
        this.contatoRepository = contatoRepository;
        this.pessoaService = pessoaService;
        this.objectMapper = objectMapper;
    }

    public List<ContatoDTO> list(){
        log.debug("Entrando na ContatoService");
        List<ContatoDTO> list = contatoRepository.list()
                .stream()
                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    public List<ContatoDTO> listById(Integer id) {
        log.debug("Entrando na ContatoService");
        List<ContatoDTO> list = contatoRepository.list()
                .stream()
                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                .filter(contatoDTO -> contatoDTO.getIdContato().equals(id))
                .collect(Collectors.toList());
        return list;
    }

    public ContatoDTO create(Integer id, ContatoCreateDTO dto) throws Exception {
        log.debug("Entrando na ContatoService");
        pessoaService.getPessoa(id);
        Contato contatoEntity = objectMapper.convertValue(dto, Contato.class);
        contatoRepository.create(id, contatoEntity);
        ContatoDTO contatoDTO = objectMapper.convertValue(contatoEntity, ContatoDTO.class);
        return contatoDTO;
    }

    public ContatoDTO update(Integer id, ContatoCreateDTO dto) throws Exception {
        log.debug("Entrando na ContatoService");
        getContato(id);
        Contato contatoEntity = objectMapper.convertValue(dto, Contato.class);
        Contato contatoRecuperado = getContato(id);
        contatoRepository.update(id, contatoEntity, contatoRecuperado);
        ContatoDTO contatoDTO = objectMapper.convertValue(contatoEntity, ContatoDTO.class);
        return contatoDTO;
    }

    public void delete(Integer id) throws Exception {
        log.debug("Entrando na ContatoService");
        getContato(id);
        Contato contatoRecuperado = getContato(id);
        contatoRepository.delete(contatoRecuperado);
    }

    private Contato getContato(Integer id) throws Exception {
        Contato contatoRecuperado = contatoRepository.list().stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado!"));
        return contatoRecuperado;
    }

}
