package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.service.EnderecoService;
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
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping // GET http://localhost:8080/endereco
    public ResponseEntity<List<EnderecoDTO>> list() {
        log.debug("Listando endereços!");
        List<EnderecoDTO> list = enderecoService.list();
        log.debug("Listagem concluida com sucesso!");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{idEndereco}") // GET http://localhost:8080/endereco/{idPessoa}
    public ResponseEntity<List<EnderecoDTO>> listById(@PathVariable("idEndereco") Integer id) {
        log.debug("Listando endereços com idEndereço!");
        List<EnderecoDTO> list = enderecoService.listById(id);
        log.debug("Listagem concluida com sucesso!");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/pessoa/{idPessoa}") // GET http://localhost:8080/endereco/pessoa/{idPessoa}
    public ResponseEntity<List<EnderecoDTO>> listByIdPessoa(@PathVariable("idPessoa") Integer id) {
        log.debug("Listando endereços com idPessoa!");
        List<EnderecoDTO> list = enderecoService.listByIdPessoa(id);
        log.debug("Listagem concluida com sucesso!");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/{idPessoa}") // POST http://localhost:8080/endereco/{idPessoa}
    public ResponseEntity<EnderecoDTO> create(@PathVariable("idPessoa") Integer id, @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        log.debug("Criando endereço!");
        EnderecoDTO enderecoCriado = enderecoService.create(id, enderecoCreateDTO);
        log.debug("Criou endereço com sucesso!");
        return new ResponseEntity<>(enderecoCriado, HttpStatus.OK);
    }

    @PutMapping("/{idPessoa}") // PUT http://localhost:8080/endereco/{idPessoa}
    public ResponseEntity<EnderecoDTO> update(@PathVariable("idPessoa") Integer id, @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        log.debug("Atualizando endereço!");
        EnderecoDTO enderecoAtualizado = enderecoService.update(id, enderecoCreateDTO);
        log.debug("Atualizado com sucesso!");
        return new ResponseEntity<>(enderecoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{idPessoa}") // DELETE http://localhost:8080/endereco/{idPessoa}
    public ResponseEntity<Void> delete(@PathVariable("idEndereco") Integer id) throws Exception {
        log.debug("Deletando um endereço!");
        enderecoService.delete(id);
        log.debug("Deletou com sucesso!");
        return ResponseEntity.ok().build();
    }
}
