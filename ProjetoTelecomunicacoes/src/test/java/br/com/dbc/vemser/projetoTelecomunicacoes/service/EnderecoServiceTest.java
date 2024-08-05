package br.com.dbc.vemser.projetoTelecomunicacoes.service;


import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoCreateDTO;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;


import org.springframework.test.util.ReflectionTestUtils;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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
//        MockitoAnnotations.openMocks(this);
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