package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dto.FaturaCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.FaturaDTO;
import br.com.dbc.vemser.pessoaapi.dto.PagamentoDTO;
import br.com.dbc.vemser.pessoaapi.service.FaturaService;
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

//    @GetMapping // GET localhost:8080/fatura
//    public ResponseEntity<List<FaturaDTO>> list() {
//        List<FaturaDTO> listaFaturas = faturaService.list();
//        return new ResponseEntity<>(listaFaturas, HttpStatus.OK);
//    }

//    @GetMapping("/{idFatura}") // GET http://localhost:8080/fatura/{idFatura}
//    public ResponseEntity<FaturaDTO> findByIdDTO(@PathVariable("idFatura") Integer id) throws Exception {
//        FaturaDTO faturaDTO = faturaService.findByIdDTO(id);
//        return new ResponseEntity<>(faturaDTO, HttpStatus.OK);
//    }

    @GetMapping("/pessoafatura/{idCliente}") // GET http://localhost:8080/fatura/{idFatura}
    public ResponseEntity<List<FaturaDTO>> listByClient(@PathVariable("idCliente") Integer id) throws Exception {
        List<FaturaDTO> listaFaturas = faturaService.listByClient(id);
        return new ResponseEntity<>(listaFaturas, HttpStatus.OK);
    }

//    @PostMapping // POST localhost:8080/pessoa
//    public ResponseEntity<FaturaDTO> create (@Valid @RequestBody FaturaCreateDTO fatura) throws Exception {
//        log.debug("Criando uma fatura!");
//        FaturaDTO faturaDTO = faturaService.create(fatura);
//        log.debug("Fatura criada com sucesso!");
//        return new ResponseEntity<>(faturaDTO, HttpStatus.OK);
//    }

    @PutMapping("/pagar/cliente/{idCliente}") // PUT localhost:8080/pessoa/1000
    public ResponseEntity<FaturaDTO> pagarFatura(@PathVariable("idCliente") Integer id, @Valid @RequestBody PagamentoDTO pagamentoDTO) throws Exception {
        FaturaDTO faturaDTO = faturaService.pagarFatura(id, pagamentoDTO.getNumeroFatura(), pagamentoDTO.getValorPago(), pagamentoDTO.getDataBaixa());
        return new ResponseEntity<>(faturaDTO, HttpStatus.OK);
    }

//    @DeleteMapping("/{idFatura}") // DELETE localhost:8080/pessoa/10
//    public ResponseEntity<Void> delete(@PathVariable("idFatura") Integer id) throws Exception {
//
//        faturaService.delete(id);
//        return ResponseEntity.ok().build();
//    }


}
