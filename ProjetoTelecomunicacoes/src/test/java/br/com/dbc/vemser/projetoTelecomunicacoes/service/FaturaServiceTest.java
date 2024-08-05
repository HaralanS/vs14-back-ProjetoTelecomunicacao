package br.com.dbc.vemser.projetoTelecomunicacoes.service;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.FaturaDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.PageDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Cliente;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Endereco;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

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

    @BeforeEach
    void init(){
        faturaMock = new FaturaMock();
        clienteMock = new ClienteMock();
        ReflectionTestUtils.setField(faturaService, "objectMapper", getObjectMapperInstance());
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


    @Test
    void testListFaturasPaginacao() throws RegraDeNegocioException {
        Integer pagina = 0;
        Integer tamanho = 10;

        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);

        Fatura fatura = new Fatura();
        fatura.setIdFatura(1);
        fatura.setCliente(cliente);

        FaturaDTO faturaDTO = new FaturaDTO();
        faturaDTO.setIdFatura(1);
        faturaDTO.setIdCliente(1);

        Page<Fatura> faturaPage = new PageImpl<>(List.of(fatura));
        when(faturaRepository.findAll(any(Pageable.class))).thenReturn(faturaPage);
        when(objectMapper.convertValue(fatura, FaturaDTO.class)).thenReturn(faturaDTO);

        PageDTO<FaturaDTO> result = faturaService.listFaturasPaginacao(pagina, tamanho);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertEquals(0, result.getPage());
        assertEquals(10, result.getSize());
        assertEquals(1, result.getContent().size());
        assertEquals(faturaDTO, result.getContent().get(0));
    }

}