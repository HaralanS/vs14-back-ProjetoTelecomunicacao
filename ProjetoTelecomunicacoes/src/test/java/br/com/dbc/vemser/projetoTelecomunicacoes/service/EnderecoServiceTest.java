package br.com.dbc.vemser.projetoTelecomunicacoes.service;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Cliente;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Endereco;
import br.com.dbc.vemser.projetoTelecomunicacoes.mocks.ClienteMock;
import br.com.dbc.vemser.projetoTelecomunicacoes.mocks.EnderecoMock;
import br.com.dbc.vemser.projetoTelecomunicacoes.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private ClienteService clienteService;

    private EnderecoMock enderecoMock;

    private ClienteMock clienteMock;

    @BeforeEach
    void init(){
        enderecoMock = new EnderecoMock();
        clienteMock = new ClienteMock();
        ReflectionTestUtils.setField(enderecoService, "objectMapper", getObjectMapperInstance());
    }


    @Test
    void deveCriarUmEnderecoComSucesso() throws Exception {
        EnderecoCreateDTO enderecoCreateDTO = enderecoMock.retornarEntidadeEnderecoCreateDTO(1);
        Endereco endereco = enderecoMock.retornarEntidadeEndereco(1);
        endereco.setCliente(clienteMock.retronaClienteEntidade(1));

        when(clienteService.getPessoa(enderecoCreateDTO.getIdPessoa())).thenReturn(clienteMock.retronaClienteEntidade(1));
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);

        EnderecoDTO enderecoDTO = enderecoService.create(enderecoCreateDTO);

        assertNotNull(enderecoDTO);
        assertEquals(enderecoCreateDTO.getIdPessoa(), enderecoDTO.getIdPessoa());
        assertEquals(enderecoCreateDTO.getCep(), enderecoDTO.getCep());
        assertEquals(enderecoCreateDTO.getLogradouro(), enderecoDTO.getLogradouro());
        assertEquals(enderecoCreateDTO.getNumero(), enderecoDTO.getNumero());
        assertEquals(enderecoCreateDTO.getComplemento(), enderecoDTO.getComplemento());
        assertEquals(enderecoCreateDTO.getCidade(), enderecoDTO.getCidade());
        assertEquals(enderecoCreateDTO.getEstado(), enderecoDTO.getEstado());
        assertEquals(enderecoCreateDTO.getTipo(), enderecoDTO.getTipo());
        assertEquals(enderecoCreateDTO.getPais(), enderecoDTO.getPais());
    }
    @Test
    void deveAtualizarUmEnderecoExistenteComSucesso() throws Exception {
        Endereco enderecoRecuperado = enderecoMock.retornarEntidadeEndereco(1);
        Integer id = 1;

        EnderecoCreateDTO dto = enderecoMock.retornarEntidadeEnderecoCreateDTO(3);


        when(clienteService.getPessoa(dto.getIdPessoa())).thenReturn(clienteMock.retronaClienteEntidade(3));
        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.of(enderecoRecuperado));
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(enderecoRecuperado);

        EnderecoDTO enderecoDTO = enderecoService.update(id, dto);

        assertNotNull(enderecoDTO);
        assertEquals(dto.getIdPessoa(), enderecoDTO.getIdPessoa());
        assertEquals(dto.getCep(), enderecoDTO.getCep());
        assertEquals(dto.getLogradouro(), enderecoDTO.getLogradouro());
        assertEquals(dto.getNumero(), enderecoDTO.getNumero());
        assertEquals(dto.getComplemento(), enderecoDTO.getComplemento());
        assertEquals(dto.getCidade(), enderecoDTO.getCidade());
        assertEquals(dto.getEstado(), enderecoDTO.getEstado());
        assertEquals(dto.getTipo(), enderecoDTO.getTipo());
        assertEquals(dto.getPais(), enderecoDTO.getPais());

    }

    public static ObjectMapper getObjectMapperInstance() {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return objectMapper;

    }


}