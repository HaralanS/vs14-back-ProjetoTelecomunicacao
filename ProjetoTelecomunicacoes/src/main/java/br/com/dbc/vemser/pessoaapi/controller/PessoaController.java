package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.documentacao.PessoaControllerDoc;
import br.com.dbc.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaDTO;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.service.PessoaService;
import br.com.dbc.vemser.pessoaapi.service.PropertieReader;
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
@RequestMapping("/pessoa") // localhost:8080/pessoa
public class PessoaController implements PessoaControllerDoc {

    private final PessoaService pessoaService;
    private final PropertieReader propertieReader;

    public PessoaController(PessoaService pessoaService, PropertieReader propertieReader) {
        this.pessoaService = pessoaService;
        this.propertieReader = propertieReader;
    }
    
    @GetMapping("/ambiente") // GET localhost:8080/pessoa/ambiente
    public ResponseEntity<String> ambiente() {

        return new ResponseEntity<>(propertieReader.getAmbiente(), HttpStatus.OK);
    }

    @GetMapping // GET localhost:8080/pessoa
    public ResponseEntity<List<PessoaDTO>> list() {
        List<PessoaDTO> listaCriada = pessoaService.list();
        return new ResponseEntity<>(listaCriada, HttpStatus.OK);
    }

    @GetMapping("/byname") // GET localhost:8080/pessoa/byname?nome=Rafa
    public ResponseEntity<List<PessoaDTO>> listByName(@RequestParam(value = "nome") String nome) {
        List<PessoaDTO> listaCriada = pessoaService.listByName(nome);
        return new ResponseEntity<>(listaCriada, HttpStatus.OK);
    }

    @PostMapping // POST localhost:8080/pessoa
    public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaCreateDTO pessoa) throws Exception {
        log.debug("Criando uma pessoa!");
        PessoaDTO pessoaCriada = pessoaService.create(pessoa);
        log.debug("Criou com sucesso!");
        return new ResponseEntity<>(pessoaCriada, HttpStatus.OK);
    }

    @PutMapping("/{idPessoa}") // PUT localhost:8080/pessoa/1000
    public ResponseEntity<PessoaDTO> update(@PathVariable("idPessoa") Integer id, @Valid @RequestBody PessoaCreateDTO pessoaAtualizar) throws Exception {
        log.debug("Atualizando uma pessoa!");
        PessoaDTO pessoaAtualizada = pessoaService.update(id, pessoaAtualizar);
        log.debug("Atualizou com sucesso!");
        return new ResponseEntity<>(pessoaAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{idPessoa}") // DELETE localhost:8080/pessoa/10
    public ResponseEntity<Void> delete(@PathVariable("idPessoa") Integer id) throws Exception {
        log.debug("Deletando uma pessoa!");
        pessoaService.delete(id);
        log.debug("Deletou com sucesso!");
        return ResponseEntity.ok().build();
    }
}
