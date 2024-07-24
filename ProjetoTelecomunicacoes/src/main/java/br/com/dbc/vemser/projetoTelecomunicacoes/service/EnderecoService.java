package br.com.dbc.vemser.projetoTelecomunicacoes.service;


import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Endereco;
import br.com.dbc.vemser.projetoTelecomunicacoes.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.projetoTelecomunicacoes.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ObjectMapper objectMapper;

    public List<EnderecoDTO> list() {
        log.debug("Entrando na EnderecoService");
        return enderecoRepository.findAll()
                .stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public EnderecoDTO listById(Integer id) throws RegraDeNegocioException {
        log.debug("Entrando na EnderecoService");
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado!"));
        return objectMapper.convertValue(endereco, EnderecoDTO.class);
    }

    public EnderecoDTO listByIdPessoa(Integer id) throws RegraDeNegocioException {
        log.debug("Entrando na EnderecoService");
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado!"));
        return objectMapper.convertValue(endereco, EnderecoDTO.class);
    }

    public EnderecoDTO create(Integer id, EnderecoCreateDTO dto) throws Exception {
        log.debug("Entrando na EnderecoService");
        Endereco enderecoEntity = objectMapper.convertValue(dto, Endereco.class);
        enderecoEntity.setIdPessoa(id);
        Endereco savedEndereco = enderecoRepository.save(enderecoEntity);
        EnderecoDTO enderecoDTO = objectMapper.convertValue(savedEndereco, EnderecoDTO.class);
        // Cliente clienteEntity = clienteService.getPessoa(id);
        // emailService.sendEmail(clienteEntity, "ce");
        return enderecoDTO;
    }

    public EnderecoDTO update(Integer id, EnderecoCreateDTO dto) throws Exception {
        log.debug("Entrando na EnderecoService");
        Endereco enderecoRecuperado = enderecoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado!"));
        Endereco enderecoEntity = objectMapper.convertValue(dto, Endereco.class);
        enderecoEntity.setIdEndereco(enderecoRecuperado.getIdEndereco());
        enderecoEntity.setIdPessoa(enderecoRecuperado.getIdPessoa());
        Endereco updatedEndereco = enderecoRepository.save(enderecoEntity);
        EnderecoDTO enderecoDTO = objectMapper.convertValue(updatedEndereco, EnderecoDTO.class);
//        Cliente clienteEntity = clienteService.getPessoa(enderecoDTO.getIdPessoa());
//        emailService.sendEmail(clienteEntity, "ue");
        return enderecoDTO;
    }

    public void delete(Integer id) throws Exception {
        log.debug("Entrando na EnderecoService");
        Endereco enderecoRecuperado = enderecoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado!"));
        enderecoRepository.delete(enderecoRecuperado);
//        Cliente clienteEntity = clienteService.getPessoa(enderecoRecuperado.getIdPessoa());
//        emailService.sendEmail(clienteEntity, "de");
    }

    private Endereco getEndereco(Integer id) throws RegraDeNegocioException {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado!"));
    }

}
