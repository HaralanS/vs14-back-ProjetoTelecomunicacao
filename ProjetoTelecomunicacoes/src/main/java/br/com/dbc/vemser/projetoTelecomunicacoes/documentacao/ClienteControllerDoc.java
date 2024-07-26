package br.com.dbc.vemser.projetoTelecomunicacoes.documentacao;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.ClienteCreateDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.ClienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

public interface ClienteControllerDoc {

    @Operation(summary = "Listar clientes.", description = "Lista todos os clientes do banco de dados.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna uma lista de clientes."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @GetMapping // GET localhost:8080/cliente
    public ResponseEntity<List<ClienteDTO>> list();

    @Operation(summary = "Listar pelo nome.", description = "Lista os clientes com o respectivo nome passado.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna uma lista de clientes."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @GetMapping("/byname") // GET localhost:8080/cliente/byname?nome=Nome
    public ResponseEntity<List<ClienteDTO>> listByName(@RequestParam(value = "nome") String nome) throws SQLException;

    @Operation(summary = "Listar pelo id.", description = "Lista o cliente com o respectivo id passado.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna uma lista de clientes."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @GetMapping("/{idCliente}") // GET localhost:8080/cliente/{idCliente}
    public ResponseEntity<List<ClienteDTO>> listById(@PathVariable("idCliente") Integer id) throws SQLException;

    @Operation(summary = "Criar um cliente.", description = "Cria um novo cliente.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o cliente criado."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @PostMapping // POST localhost:8080/cliente
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteCreateDTO cliente) throws Exception;

    @Operation(summary = "Atualizar um cliente.", description = "Atualiza um cliente pelo id dele.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o cliente atualizado."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @PutMapping("/{idCliente}") // PUT localhost:8080/cliente/{idCliente}
    public ResponseEntity<ClienteDTO> update(@PathVariable("idCliente") Integer id, @Valid @RequestBody ClienteCreateDTO clienteAtualizar) throws Exception;

    @Operation(summary = "Deletar um cliente.", description = "Deleta um cliente pelo id dele.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @DeleteMapping("/{idCliente}") // DELETE localhost:8080/cliente/{idCliente}
    public ResponseEntity<Void> delete(@PathVariable("idCliente") Integer id) throws Exception;

}
