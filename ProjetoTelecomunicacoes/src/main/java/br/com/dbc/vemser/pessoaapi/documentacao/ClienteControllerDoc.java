package br.com.dbc.vemser.pessoaapi.documentacao;

import br.com.dbc.vemser.pessoaapi.dto.ClienteCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.ClienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;


public interface ClienteControllerDoc {

//    @Operation(summary = "Listar pessoas.", description = "Lista todas as pessoas do banco.")
//    @ApiResponses(
//            value = {
//                    @ApiResponse(responseCode = "200", description = "Retorna uma lista de pessoas."),
//                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
//                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
//            }
//    )
//    @GetMapping // GET localhost:8080/pessoa
//    public ResponseEntity<List<ClienteDTO>> list();

    @Operation(summary = "Listar pelo nome.", description = "Lista a pessoa com o respectivo nome passado.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna uma pessoa do banco."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @GetMapping("/byname") // GET localhost:8080/pessoa/byname?nome=Rafa
    public ResponseEntity<List<ClienteDTO>> listByName(@RequestParam(value = "nome") String nome) throws SQLException;

    @Operation(summary = "Cria uma pessoa.", description = "Cria uma nova pessoa.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a pessoa criada."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @PostMapping // POST localhost:8080/pessoa
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteCreateDTO pessoa) throws Exception;

    @Operation(summary = "Atualizar uma pessoa.", description = "Atualiza uma pessoa pelo id dela.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a pessoa atualizada."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @PutMapping("/{idPessoa}") // PUT localhost:8080/pessoa/1000
    public ResponseEntity<ClienteDTO> update(@PathVariable("idPessoa") Integer id, @Valid @RequestBody ClienteCreateDTO pessoaAtualizar) throws Exception;

    @Operation(summary = "Deletar uma pessoa.", description = "Deleta uma pessoa pelo id dela.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a pessoa deletada."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @DeleteMapping("/{idPessoa}") // DELETE localhost:8080/pessoa/10
    public ResponseEntity<Void> delete(@PathVariable("idPessoa") Integer id) throws Exception;

}