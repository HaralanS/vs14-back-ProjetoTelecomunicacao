package br.com.dbc.vemser.projetoTelecomunicacoes.documentacao;

import br.com.dbc.vemser.projetoTelecomunicacoes.dto.FaturaDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.PagamentoDTO;
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


    @Operation(summary = "Paga uma fatura.", description = "Paga uma fatura pelo id dela.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a fatura atualizada."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")
            }
    )
    @PutMapping("/pagar/cliente/{idCliente}") // PUT localhost:8080/fatura/cliente/{idCliente}
    public ResponseEntity<String> pagarFatura(@PathVariable("idCliente") Integer id, @Valid @RequestBody PagamentoDTO pagamentoDTO) throws Exception;

}