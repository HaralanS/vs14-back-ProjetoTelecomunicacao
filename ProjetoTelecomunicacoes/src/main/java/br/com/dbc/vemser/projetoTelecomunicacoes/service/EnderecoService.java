package br.com.dbc.vemser.projetoTelecomunicacoes.service;


import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Endereco;
import br.com.dbc.vemser.projetoTelecomunicacoes.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.projetoTelecomunicacoes.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
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
    private final ClienteService clienteService;
    private final EmailService emailService;

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

    public List<EnderecoDTO> listByIdPessoa(Integer id) throws Exception {
        log.debug("Entrando na EnderecoService");
//        List<EnderecoDTO> enderecoDTOList = enderecoRepository.findEnderecoPorIdPessoa(id).stream().map(endereco -> {
//            EnderecoDTO enderecoDto = objectMapper.convertValue(endereco, EnderecoDTO.class);
//            return enderecoDto;
//        }).collect(Collectors.toList());

        List<EnderecoDTO> list = enderecoRepository.findEnderecoPorIdPessoa(id)
                .stream()
                .map(endereco -> {
                    EnderecoDTO enderecoDTO = objectMapper.convertValue(endereco, EnderecoDTO.class);
                    enderecoDTO.setIdPessoa(endereco.getCliente().getIdCliente());
                    return enderecoDTO;
                })
                .collect(Collectors.toList());
        return list;

    }

    public EnderecoDTO create(EnderecoCreateDTO dto) throws Exception {
        log.debug("Entrando na EnderecoService");
        Endereco enderecoEntity = objectMapper.convertValue(dto, Endereco.class);
        enderecoEntity.setCliente(clienteService.getPessoa(dto.getIdPessoa()));
        enderecoEntity = enderecoRepository.save(enderecoEntity);
        //emailService.sendEmail(clienteService.getPessoa(dto.getIdPessoa()), "ue");
        EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
        enderecoDTO.setIdPessoa(enderecoEntity.getCliente().getIdCliente());
        return enderecoDTO;
    }

    public EnderecoDTO update(Integer id, EnderecoCreateDTO dto) throws Exception {
        log.debug("Entrando na EnderecoService");
        Endereco enderecoRecuperado = enderecoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado!"));

        enderecoRecuperado.setTipo(dto.getTipo());
        enderecoRecuperado.setLogradouro(dto.getLogradouro());
        enderecoRecuperado.setNumero(dto.getNumero());
        enderecoRecuperado.setComplemento(dto.getComplemento());
        enderecoRecuperado.setCep(dto.getCep());
        enderecoRecuperado.setCidade(dto.getCidade());
        enderecoRecuperado.setEstado(dto.getEstado());
        enderecoRecuperado.setPais(dto.getPais());
        enderecoRecuperado.setCliente(clienteService.getPessoa(dto.getIdPessoa()));



        enderecoRepository.save(enderecoRecuperado);
        EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoRecuperado, EnderecoDTO.class);
        enderecoDTO.setIdPessoa(enderecoRecuperado.getCliente().getIdCliente());
//        Cliente clienteEntity = clienteService.getPessoa(enderecoDTO.getIdPessoa());
//        emailService.sendEmail(clienteEntity, "ue");
        return enderecoDTO;
    }

    public void delete(Integer id) throws Exception {
        log.debug("Entrando na EnderecoService");
        Endereco enderecoRecuperado = enderecoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado!"));
        enderecoRepository.delete(enderecoRecuperado);
        emailService.sendEmail(clienteService.getPessoa(id), "de");
//        Cliente clienteEntity = clienteService.getPessoa(enderecoRecuperado.getIdPessoa());
//        emailService.sendEmail(clienteEntity, "de");
    }

    private Endereco getEndereco(Integer id) throws RegraDeNegocioException {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado!"));
    }

}
