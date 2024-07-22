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

    @Operation(summary = "Cria um endereco.", description = "Cria um novo endereço.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna um endereço criado."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @PostMapping // POST localhost:8080/endereco
    public ResponseEntity<EnderecoDTO> create(Integer id, @Valid @RequestBody EnderecoCreateDTO endereco) throws Exception;

    @Operation(summary = "Atualizar um endereço.", description = "Atualiza um endereço pelo id dele.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o endereço atualizado."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @PutMapping("/{idEndereco}") // PUT localhost:8080/endereco/1000
    public ResponseEntity<EnderecoDTO> update(@PathVariable("idEndereco") Integer id, @Valid @RequestBody EnderecoCreateDTO enderecoAtualizar) throws Exception;

    @Operation(summary = "Lista um endereco.", description = "Lista um endereço pelo id dele.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna um endereço deletado."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @GetMapping("/pessoa/{idPessoa}") // GET http://localhost:8080/endereco/pessoa/{idPessoa}
    public ResponseEntity<EnderecoDTO> listByIdPessoa(@PathVariable("idPessoa") Integer id);

}