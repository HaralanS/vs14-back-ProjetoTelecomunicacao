package br.com.dbc.vemser.projetoTelecomunicacoes.documentacao;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.FaturaDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.PagamentoDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.PageDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface FaturaControllerDoc {

    @Operation(summary = "Listar faturas por id do cliente.", description = "Lista as faturas pelo id do cliente.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Faturas listadas com sucesso."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @GetMapping("/pessoafatura/{idCliente}") // GET http://localhost:8080/fatura/pessoafatura/{idCliente}
    public ResponseEntity<List<FaturaDTO>> listByIdClient(@PathVariable("idCliente") Integer id) throws Exception;

    @Operation(summary = "Pagar fatura.", description = "Paga uma fatura pelo id da fatura.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Fatura paga com sucesso."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @PutMapping("/pagar/{idFatura}") // PUT localhost:8080/fatura/pagar/cliente/{idCliente}
    public ResponseEntity<FaturaDTO> payInvoice(@PathVariable("idFatura") Integer id, @Valid @RequestBody PagamentoDTO pagamentoDTO) throws Exception;

    @Operation(summary = "Listar faturas do sistema com paginação.", description = "Listar faturas do sistema com paginação.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Faturas listadas com sucesso."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @GetMapping("/paginacao") // GET http://localhost:8080/fatura/pessoafatura/{idCliente}
    public PageDTO<FaturaDTO> listFaturasAscending(@RequestParam(value = "pagina", required = false, defaultValue = "0") Integer pagina,
                                                   @RequestParam(value = "tamanho", required = false, defaultValue = "10") Integer tamanho
    ) throws RegraDeNegocioException;



}