package br.com.dbc.vemser.projetoTelecomunicacoes.service;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Endereco;
import br.com.dbc.vemser.projetoTelecomunicacoes.exceptions.RegraDeNegocioException;
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


import org.springframework.test.util.ReflectionTestUtils;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private EnderecoRepository enderecoRepository;

    private EnderecoMock enderecoMock;

    private ClienteMock clienteMock;

    @BeforeEach
    void init(){
//        MockitoAnnotations.openMocks(this);
        enderecoMock = new EnderecoMock();
        clienteMock = new ClienteMock();
        ReflectionTestUtils.setField(enderecoService, "objectMapper", getObjectMapperInstance());
    }

    @Test
    void deveListarTodosEnderecosComSucesso() {
        // Arrange
        List<Endereco> enderecosMock = List.of(
                enderecoMock.retornarEntidadeEndereco(1),
                enderecoMock.retornarEntidadeEndereco(2),
                enderecoMock.retornarEntidadeEndereco(3)
        );

//        List<EnderecoDTO> enderecosDTO = enderecoService.list();

        when(enderecoRepository.findAll()).thenReturn(enderecosMock);

        // Act
        List<EnderecoDTO> resultado = enderecoService.list();

        // Assertions
        assertNotNull(resultado);
        assertEquals(enderecosMock.size(), resultado.size());
        assertEquals(enderecosMock.get(0).getLogradouro(), resultado.get(0).getLogradouro());
        assertEquals(enderecosMock.get(1).getLogradouro(), resultado.get(1).getLogradouro());
        assertEquals(enderecosMock.get(2).getLogradouro(), resultado.get(2).getLogradouro());
        assertEquals(enderecosMock.get(0).getCidade(), resultado.get(0).getCidade());


    }


    @Test
    void deveRetornarEnderecoDTOPorIdComSucesso() throws RegraDeNegocioException {
        // Arrange
        Integer id = 1;
        Endereco endereco = enderecoMock.retornarEntidadeEndereco(id);

        when(enderecoRepository.findById(id)).thenReturn(Optional.of(endereco));
        // Act
        EnderecoDTO resultado = enderecoService.listById(id);

        // Assertions
        assertNotNull(resultado);
        assertEquals(endereco.getCep(), resultado.getCep());
        assertEquals(endereco.getLogradouro(), resultado.getLogradouro());
        assertEquals(endereco.getNumero(), resultado.getNumero());
        assertEquals(endereco.getComplemento(), resultado.getComplemento());
        assertEquals(endereco.getCidade(), resultado.getCidade());
        assertEquals(endereco.getEstado(), resultado.getEstado());
        assertEquals(endereco.getTipo(), resultado.getTipo());
        assertEquals(endereco.getPais(), resultado.getPais());


    }


    @Test
    void deveListarTodosOsEnderecosPorIdCliente() throws Exception {
        Integer idCliente = 1;

        List<Endereco> enderecoList = List.of(enderecoMock.retornarEntidadeEndereco(1));
        when(enderecoRepository.findEnderecoPorIdPessoa(1)).thenReturn(enderecoList);

        System.out.println(enderecoList.get(0).getCliente().getIdCliente());

        List<EnderecoDTO> enderecoDTOList = enderecoService.listByIdPessoa(idCliente);

        assertNotNull(enderecoDTOList);
        assertEquals(enderecoList.get(0).getCliente().getIdCliente(), enderecoDTOList.get(0).getIdPessoa());
        assertEquals(enderecoList.get(0).getLogradouro(), enderecoDTOList.get(0).getLogradouro());
        assertEquals(enderecoList.get(0).getCidade(), enderecoDTOList.get(0).getCidade());
        assertEquals(enderecoList.get(0).getNumero(), enderecoDTOList.get(0).getNumero());
    }

    @Test
    void deveLancarExcecaoQuandoEnderecoNaoEncontrado() {
        // Arrange
        Integer id = 1;
        when(enderecoRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        RegraDeNegocioException exception = assertThrows(RegraDeNegocioException.class, () -> {
            enderecoService.listById(id);
        });

        assertEquals("Endereço não encontrado!", exception.getMessage());

        assertThrows(RegraDeNegocioException.class, () -> enderecoService.listById(anyInt()));

    }

    public static ObjectMapper getObjectMapperInstance() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
}