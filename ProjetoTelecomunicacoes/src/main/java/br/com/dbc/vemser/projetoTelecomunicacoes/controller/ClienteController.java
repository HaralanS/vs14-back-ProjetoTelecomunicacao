package br.com.dbc.vemser.projetoTelecomunicacoes.controller;

import br.com.dbc.vemser.projetoTelecomunicacoes.documentacao.ClienteControllerDoc;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.ClienteCreateDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.ClienteDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.ClienteTela1DTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.service.ClienteService;
import br.com.dbc.vemser.projetoTelecomunicacoes.service.PropertieReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/cliente") // localhost:8080/cliente
public class ClienteController implements ClienteControllerDoc {

    private final ClienteService clienteService;
    private final PropertieReader propertieReader;

    public ClienteController(ClienteService clienteService, PropertieReader propertieReader) {
        this.clienteService = clienteService;
        this.propertieReader = propertieReader;
    }

    @GetMapping // GET localhost:8080/cliente
    public ResponseEntity<List<ClienteDTO>> list() {
        log.debug("Listando clientes!");
        List<ClienteDTO> listaCriada = clienteService.list();
        log.debug("Listagem concluida com sucesso!");
        return new ResponseEntity<>(listaCriada, HttpStatus.OK);
    }

    @GetMapping("/byname") // GET localhost:8080/cliente/byname?nome=Juan
    public ResponseEntity<List<ClienteDTO>> listByName(@RequestParam(value = "nome") String nome) throws SQLException {
        log.debug("Listando cliente por nome!");
        List<ClienteDTO> listaCriada = clienteService.listByName(nome);
        log.debug("Listando cliente por nome concluido!");
        return new ResponseEntity<>(listaCriada, HttpStatus.OK);
    }

    @GetMapping("/{idCliente}") // GET localhost:8080/cliente/byname?nome=Rafa
    public ResponseEntity<List<ClienteDTO>> listById(@PathVariable("idCliente") Integer id) throws SQLException {
        log.debug("Listando cliente por id.");
        List<ClienteDTO> listaCriada = clienteService.listById(id);
        log.debug("Listando por id concluido!");
        return new ResponseEntity<>(listaCriada, HttpStatus.OK);
    }

    @PostMapping // POST localhost:8080/cliente
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteCreateDTO pessoa) throws Exception {

        log.debug("Criando uma pessoa!");
        ClienteDTO clienteCriado = clienteService.createCliente(pessoa);
        log.debug("Criou com sucesso!");
        return new ResponseEntity<>(clienteCriado, HttpStatus.OK);
    }

    @PutMapping("/{idCliente}") // PUT localhost:8080/cliente/1
    public ResponseEntity<ClienteDTO> update(@PathVariable("idCliente") Integer id, @Valid @RequestBody ClienteCreateDTO pessoaAtualizar) throws Exception {
        log.debug("Atualizando uma cliente!");
        ClienteDTO clienteAtualizado = clienteService.update(id, pessoaAtualizar);
        log.debug("Atualizou com sucesso!");
        return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{idCliente}") // DELETE localhost:8080/cliente/10
    public ResponseEntity<Void> delete(@PathVariable("idCliente") Integer id) throws Exception {
        log.debug("Deletando uma cliente!");
        clienteService.delete(id);
        log.debug("Deletou com sucesso!");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tela1e2/{idCliente}") // DELETE localhost:8080/cliente/10
    public ResponseEntity<ClienteTela1DTO> findClienteTela1ById(@PathVariable("idCliente") Integer id) throws Exception {
        log.debug("Buscando cliente pra tela 1 e 2!");
        ClienteTela1DTO clienteTela1DTO = clienteService.findClienteTela1ById(id);
        return new ResponseEntity<>(clienteTela1DTO, HttpStatus.OK);
    }


}
