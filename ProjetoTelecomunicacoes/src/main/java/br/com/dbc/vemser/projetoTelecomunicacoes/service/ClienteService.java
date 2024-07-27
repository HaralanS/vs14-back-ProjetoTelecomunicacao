package br.com.dbc.vemser.projetoTelecomunicacoes.service;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.*;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Cliente;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Fatura;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.planos.Plano;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.planos.PlanoBasico;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.planos.PlanoMedium;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.planos.PlanoPremium;
import br.com.dbc.vemser.projetoTelecomunicacoes.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.projetoTelecomunicacoes.repository.ClienteRepository;

import br.com.dbc.vemser.projetoTelecomunicacoes.repository.FaturaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;
    private final FaturaRepository faturaRepository;

    public ClienteService(ClienteRepository clienteRepository, ObjectMapper objectMapper, EmailService emailService, FaturaRepository faturaRepository){
        this.clienteRepository = clienteRepository;
        this.objectMapper = objectMapper;
        this.emailService = emailService;
        this.faturaRepository = faturaRepository;
    }

    public List<ClienteDTO> list(){
        List<ClienteDTO> list = clienteRepository.findAll()
                .stream()
                .map(cliente -> objectMapper.convertValue(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    public List<ClienteDTO> listByName(String nome) throws SQLException {
        List<ClienteDTO> list = clienteRepository.findAllByNomeContainsIgnoreCase(nome)
                .stream()
                .map(cliente -> objectMapper.convertValue(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    public List<ClienteDTO> listById(Integer id) throws SQLException {
        List<ClienteDTO> list = clienteRepository.findById(id)
                .stream()
                .map(cliente -> objectMapper.convertValue(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
        return list;
    }
    public ClienteTela1DTO findClienteTela1ById(Integer id) throws SQLException, RegraDeNegocioException {

        Optional<Cliente> cliente = clienteRepository.findClienteTela1ById(id);
        if (cliente == null){
            throw new RegraDeNegocioException("Nao existem clientes com o id: " + id);
        }
        ClienteTela1DTO clienteTela1DTO = objectMapper.convertValue(cliente, ClienteTela1DTO.class);
        return clienteTela1DTO;
    }

    public ClienteDTO createCliente(ClienteCreateDTO dto) throws Exception {


        log.debug("Entrando na ClienteService");
        Cliente clienteEntity = objectMapper.convertValue(dto, Cliente.class);
        clienteEntity = clienteRepository.save(clienteEntity);

        System.out.println("- - - - - -  - - - - - - - - - - - - - -- - - -  - -");
        System.out.println("- - - - - -  - - - - - - - - - - - - - -- - - -  - -");
        System.out.println("- - - - - - create cliente service entrou - - - - - -  - -");
        System.out.println("tipo de plano:" + dto.getTipoDePlano());
        System.out.println("tipo de plano:" + clienteEntity.getIdCliente());
        System.out.println("- - - - - -  - - - - - - - - - - - - - -- - - -  - -");
        System.out.println("- - - - - -  - - - - - - - - - - - - - -- - - -  - -");

        ClienteDTO clienteDTO = objectMapper.convertValue(clienteEntity, ClienteDTO.class);
//        emailService.sendEmail(clienteEntity, "cp");

//        Plano plano = null;
//        switch (clienteEntity.getTipoDePlano()){
//            case BASICO:
//                plano = new PlanoBasico();
//                break;
//            case MEDIUM:
//                plano = new PlanoMedium();
//                break;
//            case PREMIUM:
//                plano = new PlanoPremium();
//                break;
//        }

        System.out.println(plano.getValor());
        LocalDate dataAtual = LocalDate.now();
        for (int i = 0; i < 12; i++) {
            LocalDate dataVencimento = dataAtual.plusMonths((i + 1));
            FaturaCreateDTO faturaCreateDTO = new FaturaCreateDTO(clienteEntity.getIdCliente(), dataVencimento, null, plano.getValor(), 0, (Integer) (i+1));
            System.out.println("id: " + faturaCreateDTO.getIdCliente() + "Dtvncmnt: " + faturaCreateDTO.getDataVencimento() + "Dtbx: " + faturaCreateDTO.getDataBaixa() + "prcla: " + faturaCreateDTO.getParcelaDoPlano() + "vlor pago: " + faturaCreateDTO.getValorPago() + "n fatura: " + faturaCreateDTO.getNumeroFatura());
            Fatura fatura = objectMapper.convertValue(faturaCreateDTO, Fatura.class);
            log.debug("Criando fatura após criar cliente");
            faturaRepository.save(fatura);
        }

        return clienteDTO;
    }

    public ClienteDTO update(Integer id, ClienteCreateDTO dto) throws Exception {
        log.debug("Entrando na PessoaService");
        Cliente clienteEntity = getPessoa(id);

        clienteEntity.setCpf(dto.getCpf());
        clienteEntity.setEmail(dto.getEmail());
        clienteEntity.setNumeroTelefone(dto.getNumeroTelefone());
        clienteEntity.setDataNascimento(dto.getDataNascimento());
        clienteEntity.setNome(dto.getNome());

        clienteRepository.save(clienteEntity);
        ClienteDTO clienteDTO = objectMapper.convertValue(clienteEntity, ClienteDTO.class);
        return clienteDTO;
    }

    public void delete(Integer id) throws Exception {
        log.debug("Entrando na PessoaService");
        clienteRepository.deleteById(id);
    }

    public Cliente getPessoa(Integer id) throws Exception {
        return clienteRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("Cliente de id " + id + " nao encontrado"));
    }

} 
