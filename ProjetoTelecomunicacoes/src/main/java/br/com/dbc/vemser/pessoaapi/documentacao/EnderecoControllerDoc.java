package br.com.dbc.vemser.pessoaapi.documentacao;

import br.com.dbc.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


public interface EnderecoControllerDoc {

    @Operation(summary = "Listar endereços.", description = "Lista todas os endereços do banco de dados.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna uma lista de pessoas."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @GetMapping // GET localhost:8080/endereco
    public ResponseEntity<List<EnderecoDTO>> list();

    @Operation(summary = "Cria um endereco.", description = "Cria um novo endereço.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna um endereço criado."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @PostMapping // POST localhost:8080/endereco
    public ResponseEntity<EnderecoDTO> create(@Valid @RequestBody EnderecoCreateDTO endereco) throws Exception;

    @Operation(summary = "Atualizar um endereço.", description = "Atualiza um endereço pelo id dele.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a pessoa atualizada."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @PutMapping("/{idEndereco}") // PUT localhost:8080/endereco/1000
    public ResponseEntity<EnderecoDTO> update(@PathVariable("idEndereco") Integer id, @Valid @RequestBody EnderecoCreateDTO enderecoAtualizar) throws Exception;

    @Operation(summary = "Deletar um endereco.", description = "Deleta um endereço pelo id dele.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna um endereço deletado."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @DeleteMapping("/{idEndereco}") // DELETE localhost:8080/endereco/10
    public ResponseEntity<Void> delete(@PathVariable("idEndereco") Integer id) throws Exception;

}