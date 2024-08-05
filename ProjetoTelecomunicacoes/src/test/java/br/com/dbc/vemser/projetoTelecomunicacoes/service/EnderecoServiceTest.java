package br.com.dbc.vemser.projetoTelecomunicacoes.service;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Cliente;
import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Endereco;
import br.com.dbc.vemser.projetoTelecomunicacoes.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.projetoTelecomunicacoes.mocks.ClienteMock;
import br.com.dbc.vemser.projetoTelecomunicacoes.mocks.EnderecoMock;
import br.com.dbc.vemser.projetoTelecomunicacoes.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import liquibase.pro.packaged.E;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
//        MockitoAnnotations.openMocks(this);
        enderecoMock = new EnderecoMock();
        clienteMock = new ClienteMock();
        ReflectionTestUtils.setField(enderecoService, "objectMapper", getObjectMapperInstance());
    }

    public static ObjectMapper getObjectMapperInstance() {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return objectMapper;

    }

//    listByIdPessoa - Haralan

    @Test
    void deveListarTodosOsEnderecosPorIdCliente() throws Exception {
        Integer idCliente = 1;

        List<Endereco> enderecoList = List.of(enderecoMock.retornarEntidadeEndereco(1));
        when(enderecoRepository.findEnderecoPorIdPessoa(1)).thenReturn(enderecoList);

        List<EnderecoDTO> enderecoDTOList = enderecoService.listByIdPessoa(idCliente);

        assertNotNull(enderecoDTOList);
        assertEquals(enderecoList.get(0).getCliente().getIdCliente(), enderecoDTOList.get(0).getIdPessoa());
        assertEquals(enderecoList.get(0).getLogradouro(), enderecoDTOList.get(0).getLogradouro());
        assertEquals(enderecoList.get(0).getCidade(), enderecoDTOList.get(0).getCidade());
        assertEquals(enderecoList.get(0).getNumero(), enderecoDTOList.get(0).getNumero());
    }

    @Test
    void TestarPegarEndereco() throws RegraDeNegocioException {
        Integer enderecoId = 1;
        Endereco endereco = new Endereco();
        endereco.setIdEndereco(enderecoId);

        when(enderecoRepository.findById(enderecoId)).thenReturn(Optional.of(endereco));

        Endereco result = enderecoService.getEndereco(enderecoId);

        assertEquals(enderecoId, result.getIdEndereco());
    }

    @Test
    void testDeleteEndereco() throws Exception {
        Integer enderecoId = 1;
        Endereco endereco = new Endereco();
        endereco.setIdEndereco(enderecoId);

        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);

        when(enderecoRepository.findById(enderecoId)).thenReturn(Optional.of(endereco));

        enderecoService.delete(enderecoId);

        verify(enderecoRepository).delete(endereco);
    }

//    @Test
//    void testarEnderecoNaoEncontrado() {
//        Integer enderecoId = 1;
//
//        when(enderecoRepository.findById(enderecoId)).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(RegraDeNegocioException.class, () -> {
//            enderecoService.getEndereco(enderecoId);
//        });
//
//        assertEquals("Endereço não encontrado!", exception.getMessage());
//    }
//
//    @Test
//    void TestarEnderecoNaoEncontrado() {
//        Integer enderecoId = 1;
//
//        when(enderecoRepository.findById(enderecoId)).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(RegraDeNegocioException.class, () -> {
//            enderecoService.delete(enderecoId);
//        });
//
//        assertEquals("Endereço não encontrado!", exception.getMessage());
//        verify(enderecoRepository, never()).delete(any(Endereco.class));
//    }

}