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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        enderecoMock = new EnderecoMock();
        clienteMock = new ClienteMock();
    }

    @Test
    void deveListarTodosEnderecosComSucesso() {
        // Arrange
        List<Endereco> enderecosMock = List.of(
                enderecoMock.retornarEntidadeEndereco(1),
                enderecoMock.retornarEntidadeEndereco(2),
                enderecoMock.retornarEntidadeEndereco(3)
        );

        List<EnderecoDTO> enderecosDTO = List.of(
                enderecoMock.retornarEntidadeEnderecoDTO(1),
                enderecoMock.retornarEntidadeEnderecoDTO(2),
                enderecoMock.retornarEntidadeEnderecoDTO(3)
        );

        when(enderecoRepository.findAll()).thenReturn(enderecosMock);
        when(objectMapper.convertValue(enderecosMock.get(0), EnderecoDTO.class)).thenReturn(enderecosDTO.get(0));
        when(objectMapper.convertValue(enderecosMock.get(1), EnderecoDTO.class)).thenReturn(enderecosDTO.get(1));
        when(objectMapper.convertValue(enderecosMock.get(2), EnderecoDTO.class)).thenReturn(enderecosDTO.get(2));

        // Act
        List<EnderecoDTO> resultado = enderecoService.list();

        // Assertions
        assertNotNull(resultado);
        assertEquals(enderecosDTO.size(), resultado.size());
        assertEquals(enderecosDTO, resultado);

        verify(enderecoRepository).findAll();
        verify(objectMapper).convertValue(enderecosMock.get(0), EnderecoDTO.class);
        verify(objectMapper).convertValue(enderecosMock.get(1), EnderecoDTO.class);
        verify(objectMapper).convertValue(enderecosMock.get(2), EnderecoDTO.class);
    }

    @Test
    void deveRetornarEnderecoDTOPorIdComSucesso() throws RegraDeNegocioException {
        // Arrange
        Integer id = 1;
        Endereco endereco = enderecoMock.retornarEntidadeEndereco(id);
        EnderecoDTO enderecoDTO = enderecoMock.retornarEntidadeEnderecoDTO(id);

        when(enderecoRepository.findById(id)).thenReturn(Optional.of(endereco));
        when(objectMapper.convertValue(endereco, EnderecoDTO.class)).thenReturn(enderecoDTO);

        // Act
        EnderecoDTO resultado = enderecoService.listById(id);

        // Assertions
        assertNotNull(resultado);
        assertEquals(enderecoDTO, resultado);

        verify(enderecoRepository).findById(id);
        verify(objectMapper).convertValue(endereco, EnderecoDTO.class);
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

        verify(enderecoRepository).findById(id);
        verify(objectMapper, never()).convertValue(any(Endereco.class), eq(EnderecoDTO.class));
    }

    public static ObjectMapper getObjectMapperInstance() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
}