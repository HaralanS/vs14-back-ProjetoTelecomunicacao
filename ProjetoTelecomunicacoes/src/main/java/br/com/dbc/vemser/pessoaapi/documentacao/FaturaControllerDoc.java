package br.com.dbc.vemser.pessoaapi.documentacao;

import br.com.dbc.vemser.pessoaapi.dto.FaturaDTO;
import br.com.dbc.vemser.pessoaapi.dto.ClienteCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.ClienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


public interface FaturaControllerDoc {

    @Operation(summary = "Lista uma fatura.", description = "Lista a fatura pelo id da pessoa.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a fatura."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @GetMapping("/pessoa/{idCliente}") // GET http://localhost:8080/fatura/{idFatura}
    public ResponseEntity<List<FaturaDTO>> listByClient(@PathVariable("idCliente") Integer id) throws Exception;


    @Operation(summary = "Cria uma fatura.", description = "Cria uma nova fatura.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a fatura criada."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @PostMapping // POST localhost:8080/fatura
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteCreateDTO pessoa) throws Exception;

    @Operation(summary = "Atualizar uma fatura.", description = "Atualiza uma fatura pelo id dela.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a fatura atualizada."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @PutMapping("/{idPessoa}") // PUT localhost:8080/fatura/1
    public ResponseEntity<ClienteDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody ClienteCreateDTO pessoaAtualizar) throws Exception;


}