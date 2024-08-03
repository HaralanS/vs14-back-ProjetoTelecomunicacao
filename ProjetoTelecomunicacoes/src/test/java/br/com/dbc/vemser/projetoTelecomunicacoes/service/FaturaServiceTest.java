package br.com.dbc.vemser.projetoTelecomunicacoes.service;

import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Fatura;
import br.com.dbc.vemser.projetoTelecomunicacoes.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.projetoTelecomunicacoes.mocks.ClienteMock;
import br.com.dbc.vemser.projetoTelecomunicacoes.mocks.FaturaMock;
import br.com.dbc.vemser.projetoTelecomunicacoes.repository.FaturaRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FaturaServiceTest {


    @InjectMocks
    private FaturaService faturaService;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private FaturaRepository faturaRepository;

    private FaturaMock faturaMock;

    private ClienteMock clienteMock;

    @BeforeEach
    void init(){
        faturaMock = new FaturaMock();
        clienteMock = new ClienteMock();
        ReflectionTestUtils.setField(faturaService, "objectMapper", getObjectMapperInstance());
    }

    @Test
    void deveRetornarFaturaPorIdComSucesso() throws Exception {
        // Arrange
        Integer id = 1;
        Fatura fatura = faturaMock.retornarEntidadeFatura(id); // Assume que FaturaMock tem esse método
        when(faturaRepository.findById(id)).thenReturn(Optional.of(fatura));

        // Act
        Fatura resultado = faturaService.getFatura(id);

        // Assertions
        assertNotNull(resultado);
        assertEquals(fatura, resultado);

        verify(faturaRepository).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoFaturaNaoEncontrada() {
        // Arrange
        Integer id = 1;
        when(faturaRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        RegraDeNegocioException exception = assertThrows(RegraDeNegocioException.class, () -> {
            faturaService.getFatura(id);
        });

        assertEquals("Fatura de id " + id + " nao encontrada", exception.getMessage());

        verify(faturaRepository).findById(id);
    }

    public static ObjectMapper getObjectMapperInstance() {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return objectMapper;
    }

}