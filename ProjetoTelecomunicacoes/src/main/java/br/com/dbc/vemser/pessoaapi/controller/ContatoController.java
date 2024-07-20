package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.ContatoDTO;
import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.ContatoService;
import br.com.dbc.vemser.pessoaapi.service.PessoaService;
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
@RequestMapping("/contato")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @GetMapping // GET http://localhost:8080/contato
    public ResponseEntity<List<ContatoDTO>> list() {
        log.debug("Listando contato!");
        List<ContatoDTO> list = contatoService.list();
        log.debug("Listagem concluida com sucesso!");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/byid") // GET http://localhost:8080/contato/byid?idContato=1
    public ResponseEntity<List<ContatoDTO>> listById(@RequestParam(value = "idContato") Integer id) {
        log.debug("Listando contato via id!");
        List<ContatoDTO> list = contatoService.listById(id);
        log.debug("Listagem concluida com sucesso!");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/byid") // POST http://localhost:8080/contato/byid?idPessoa=1
    public ResponseEntity<ContatoDTO> create(@RequestParam(value = "idPessoa") Integer id, @Valid @RequestBody ContatoCreateDTO contatoCreateDTO) throws Exception {
        log.debug("Criando contato!");
        ContatoDTO contatoCriado = contatoService.create(id, contatoCreateDTO);
        log.debug("Contato criado com sucesso!");
        return new ResponseEntity<>(contatoCriado, HttpStatus.OK);
    }

    @PutMapping("/{idContato}") // PUT http://localhost:8080/contato/{idContato}
    public ResponseEntity<ContatoDTO> update(@PathVariable("idContato") Integer id, @Valid @RequestBody ContatoCreateDTO contatoCreateDTO) throws Exception {
        log.debug("Atualizando contato!");
        ContatoDTO contatoAtualizado = contatoService.update(id, contatoCreateDTO);
        log.debug("Atualizado com sucesso!");
        return new ResponseEntity<>(contatoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{idContato}") // DELETE http://localhost:8080/contato/{idContato}
    public ResponseEntity<Void> delete(@PathVariable("idContato") Integer id) throws Exception {
        log.debug("Deletando uma pessoa!");
        contatoService.delete(id);
        log.debug("Deletou com sucesso!");
        return ResponseEntity.ok().build();
    }

}
