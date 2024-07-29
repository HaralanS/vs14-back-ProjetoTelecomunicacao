package br.com.dbc.vemser.projetoTelecomunicacoes.documentacao;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface EnderecoControllerDoc {

    @Operation(summary = "Criar um endereço.", description = "Cria um novo endereço para um cliente.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereço criado com sucesso."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @PostMapping("/{idPessoa}") // POST localhost:8080/endereco/{idPessoa}
    public ResponseEntity<EnderecoDTO> createEndereco(@PathVariable("idPessoa") Integer id, @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception;

    @Operation(summary = "Atualizar um endereço.", description = "Atualiza um endereço pelo id.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @PutMapping("/{id}") // PUT localhost:8080/endereco/{idPessoa}
    public ResponseEntity<EnderecoDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception;


    @Operation(summary = "Listar endereço por id do cliente.", description = "Lista o endereço pelo id do cliente.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereço listado com sucesso."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @GetMapping("/pessoa/{idPessoa}") // GET http://localhost:8080/endereco/pessoa/{idPessoa}
    public ResponseEntity<EnderecoDTO> listByIdPessoa(@PathVariable("idPessoa") Integer id) throws Exception;

}
