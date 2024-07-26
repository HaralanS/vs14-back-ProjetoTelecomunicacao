package br.com.dbc.vemser.projetoTelecomunicacoes.controller;

import br.com.dbc.vemser.projetoTelecomunicacoes.documentacao.FaturaControllerDoc;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.FaturaDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.PagamentoDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.service.FaturaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/fatura") // localhost:8080/pessoa
public class FaturaController {

    private final FaturaService faturaService;

    public FaturaController(FaturaService faturaService) {
        this.faturaService = faturaService;
    }

//    @GetMapping("/pessoafatura/{idCliente}") // GET
//    public ResponseEntity<List<FaturaDTO>> listByIdClient(@PathVariable("idCliente") Integer id)  {
//        log.debug("Listando fatura por id do cliente");
//        List<FaturaDTO> list = faturaService.listByIdClient(id);
//        log.debug("Listagem concluida");
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }
//
//    @PutMapping("/pagar/cliente/{idCliente}") // PUT localhost:8080/fatura/pagar/{idCliente}
//    public ResponseEntity<List<FaturaDTO>> payInvoice(@PathVariable("idCliente") Integer id, @Valid @RequestBody PagamentoDTO pagamentoDTO) throws Exception {
//        log.debug("Pagando fatura!");
//        List<FaturaDTO> list = faturaService.payInvoice(id, pagamentoDTO);
//        log.debug("Pagamento de fatura concluido");
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }

}
