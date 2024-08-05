package br.com.dbc.vemser.projetoTelecomunicacoes.service;



import br.com.dbc.vemser.projetoTelecomunicacoes.dto.PagamentoDTO;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.FaturaDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Endereco;

import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Fatura;
import br.com.dbc.vemser.projetoTelecomunicacoes.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.projetoTelecomunicacoes.mocks.ClienteMock;
import br.com.dbc.vemser.projetoTelecomunicacoes.mocks.FaturaMock;
import br.com.dbc.vemser.projetoTelecomunicacoes.mocks.PagamentoMock;
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



import static org.mockito.ArgumentMatchers.anyInt;


import java.util.Optional;

import static org.mockito.Mockito.verify;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


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

    private PagamentoMock pagamentoMock;

    @BeforeEach
    void init(){
        faturaMock = new FaturaMock();
        clienteMock = new ClienteMock();
        ReflectionTestUtils.setField(faturaService, "objectMapper", getObjectMapperInstance());
    }

    @Test

    void devePagarFaturaComSucesso() throws Exception {
        Integer id = 1;
        pagamentoMock = new PagamentoMock();
        PagamentoDTO pagamentoDTO = pagamentoMock.retornaPagamentoDTO(1);
        Fatura fatura = faturaMock.retornarEntidadeFatura(1);

        when(faturaRepository.findById(anyInt())).thenReturn(Optional.of(fatura));
        when(faturaRepository.save(any(Fatura.class))).thenReturn(fatura);

        FaturaDTO faturaPaga = faturaService.pagarFatura(id, pagamentoDTO);

        assertNotNull(faturaPaga);
        assertEquals(faturaPaga.getDataBaixa(), pagamentoDTO.getDataBaixa());
        assertEquals(faturaPaga.getValorPago(), pagamentoDTO.getValorPago());



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

//    createFatura - Haralan

    @Test
    void deveCriarUmaFaturaComSucesso() {
        Fatura fatura = faturaMock.retornarEntidadeFatura(1);

        when(faturaRepository.save(any(Fatura.class))).thenReturn(fatura);

        FaturaDTO faturaDTOCriar = faturaMock.retornarEntidadeFaturaDTO(1);
        FaturaDTO faturaDTOResultante = faturaService.create(faturaDTOCriar);

        assertNotNull(faturaDTOResultante);
        assertEquals(fatura.getIdFatura(), faturaDTOResultante.getIdFatura());
        assertEquals(fatura.getNumeroFatura(), faturaDTOResultante.getNumeroFatura());
        assertEquals(fatura.getDataVencimento(), faturaDTOResultante.getDataVencimento());
    }

//    listByIdCliente - Haralan
    @Test
    void deveListarTodosAsFaturasPorIdCliente() throws Exception {
        Integer idCliente = 1;

        Fatura fatura1 = faturaMock.retornarEntidadeFatura(1);
        fatura1.setCliente(clienteMock.retronaClienteEntidade(1));
        Fatura fatura2 = faturaMock.retornarEntidadeFatura(2);
        fatura1.setCliente(clienteMock.retronaClienteEntidade(1));
        Fatura fatura3 = faturaMock.retornarEntidadeFatura(3);
        fatura1.setCliente(clienteMock.retronaClienteEntidade(1));

        List<Fatura> faturas = List.of(
                fatura1,
                fatura2,
                fatura3
        );
        when(faturaRepository.findFaturaPorIdPessoa(1)).thenReturn(faturas);

        List<FaturaDTO> faturaDTOList = faturaService.listByIdClient(idCliente);

        assertNotNull(faturaDTOList);
        assertEquals(faturas.get(0).getCliente().getIdCliente(), faturaDTOList.get(0).getIdCliente());
        assertEquals(faturas.get(1).getCliente().getIdCliente(), faturaDTOList.get(1).getIdCliente());
        assertEquals(faturas.get(2).getCliente().getIdCliente(), faturaDTOList.get(2).getIdCliente());
        assertEquals(faturas.get(0).getDataBaixa(), faturaDTOList.get(0).getDataBaixa());
        assertEquals(faturas.get(1).getDataBaixa(), faturaDTOList.get(1).getDataBaixa());
        assertEquals(faturas.get(2).getDataBaixa(), faturaDTOList.get(2).getDataBaixa());

    }

}