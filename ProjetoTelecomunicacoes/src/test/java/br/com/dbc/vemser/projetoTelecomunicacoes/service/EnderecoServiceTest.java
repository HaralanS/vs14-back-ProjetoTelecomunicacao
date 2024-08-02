package br.com.dbc.vemser.projetoTelecomunicacoes.service;

import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Endereco;
import br.com.dbc.vemser.projetoTelecomunicacoes.mocks.ClienteMock;
import br.com.dbc.vemser.projetoTelecomunicacoes.mocks.EnderecoMock;
import br.com.dbc.vemser.projetoTelecomunicacoes.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import liquibase.pro.packaged.E;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

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
        ReflectionTestUtils.setField(enderecoService, "objectMapper", getObjectMapperInstance());
    }

    public static ObjectMapper getObjectMapperInstance() {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return objectMapper;

    }


}