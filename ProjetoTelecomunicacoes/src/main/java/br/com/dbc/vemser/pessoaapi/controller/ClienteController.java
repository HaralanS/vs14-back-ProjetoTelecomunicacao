package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.documentacao.ClienteControllerDoc;
import br.com.dbc.vemser.pessoaapi.dto.ClienteCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.ClienteDTO;
import br.com.dbc.vemser.pessoaapi.service.ClienteService;
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
@RequestMapping("/cliente") // localhost:8080/pessoa
public class ClienteController implements ClienteControllerDoc {

    private final ClienteService clienteService;
    private final PropertieReader propertieReader;

    public ClienteController(ClienteService clienteService, PropertieReader propertieReader) {
        this.clienteService = clienteService;
        this.propertieReader = propertieReader;
    }
    
    @GetMapping("/ambiente") // GET localhost:8080/pessoa/ambiente
    public ResponseEntity<String> ambiente() {

        return new ResponseEntity<>(propertieReader.getAmbiente(), HttpStatus.OK);
    }

    @GetMapping // GET localhost:8080/pessoa
    public ResponseEntity<List<ClienteDTO>> list() {
        List<ClienteDTO> listaCriada = clienteService.list();
        return new ResponseEntity<>(listaCriada, HttpStatus.OK);
    }

    @GetMapping("/byname") // GET localhost:8080/pessoa/byname?nome=Rafa
    public ResponseEntity<List<ClienteDTO>> listByName(@RequestParam(value = "nome") String nome) {
        List<ClienteDTO> listaCriada = clienteService.listByName(nome);
        return new ResponseEntity<>(listaCriada, HttpStatus.OK);
    }

    // listById

    @PostMapping // POST localhost:8080/pessoa
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteCreateDTO pessoa) throws Exception {
        log.debug("Criando uma pessoa!");
        ClienteDTO clienteCriado = clienteService.create(pessoa);
        log.debug("Criou com sucesso!");
        return new ResponseEntity<>(clienteCriado, HttpStatus.OK);
    }

    @PutMapping("/{idCliente}") // PUT localhost:8080/pessoa/1000
    public ResponseEntity<ClienteDTO> update(@PathVariable("idCliente") Integer id, @Valid @RequestBody ClienteCreateDTO pessoaAtualizar) throws Exception {
        log.debug("Atualizando uma pessoa!");
        ClienteDTO clienteAtualizado = clienteService.update(id, pessoaAtualizar);
        log.debug("Atualizou com sucesso!");
        return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{idCliente}") // DELETE localhost:8080/pessoa/10
    public ResponseEntity<Void> delete(@PathVariable("idCliente") Integer id) throws Exception {
        log.debug("Deletando uma pessoa!");
        clienteService.delete(id);
        log.debug("Deletou com sucesso!");
        return ResponseEntity.ok().build();
    }
}
