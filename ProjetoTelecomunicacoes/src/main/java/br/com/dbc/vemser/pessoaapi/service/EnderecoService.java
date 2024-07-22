package br.com.dbc.vemser.pessoaapi.service;


import br.com.dbc.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.entity.Cliente;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ClienteService clienteService;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    public EnderecoService(EnderecoRepository enderecoRepository, ClienteService clienteService, ObjectMapper objectMapper, EmailService emailService) {
        this.enderecoRepository = enderecoRepository;
        this.clienteService = clienteService;
        this.objectMapper = objectMapper;
        this.emailService = emailService;
    }

//    public List<EnderecoDTO> list(){
//        log.debug("Entrando na EnderecoService");
//        List<EnderecoDTO> list = enderecoRepository.list()
//                .stream()
//                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
//                .collect(Collectors.toList());
//        return list;
//    }

//    public List<EnderecoDTO> listById(Integer id) {
//        log.debug("Entrando na EnderecoService");
//        List<EnderecoDTO> list = enderecoRepository.list()
//                .stream()
//                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
//                .filter(enderecoDTO -> enderecoDTO.getIdEndereco().equals(id))
//                .collect(Collectors.toList());
//        return list;
//    }

    public EnderecoDTO listByIdPessoa(Integer id) {
        log.debug("Entrando na EnderecoService");
        EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoRepository.getEnderecoByIdPessoa(id), EnderecoDTO.class);
        return enderecoDTO;
    }

    public EnderecoDTO create(Integer id, EnderecoCreateDTO dto) throws Exception {
        log.debug("Entrando na EnderecoService");
        Endereco enderecoEntity = objectMapper.convertValue(dto, Endereco.class);
        enderecoRepository.create(id, enderecoEntity);
        EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
//        Cliente clienteEntity = clienteService.getPessoa(id);
//        emailService.sendEmail(clienteEntity, "ce");
        return enderecoDTO;
    }

    public EnderecoDTO update(Integer id, EnderecoCreateDTO dto) throws Exception {
        log.debug("Entrando na EnderecoService");
        clienteService.getPessoa(id);
        Endereco enderecoEntity = objectMapper.convertValue(dto, Endereco.class);
        Endereco enderecoRecuperado = getEndereco(id);
        enderecoRepository.update(id, enderecoEntity, enderecoRecuperado);
        EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
//        Cliente clienteEntity = clienteService.getPessoa(enderecoDTO.getIdPessoa());
//        emailService.sendEmail(clienteEntity, "ue");
        return enderecoDTO;
    }

//    public void delete(Integer id) throws Exception {
//        log.debug("Entrando na EnderecoService");
//        Endereco enderecoRecuperado = getEndereco(id);
//        enderecoRepository.delete(enderecoRecuperado);
//        Cliente clienteEntity = clienteService.getPessoa(enderecoRecuperado.getIdPessoa());
//        emailService.sendEmail(clienteEntity, "de");
//    }

    private Endereco getEndereco(Integer id) throws Exception {
        Endereco enderecoRecuperado = enderecoRepository.list().stream()
                .filter(endereco ->endereco.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado!"));
        return enderecoRecuperado;
    }

}
