package br.com.dbc.vemser.projetoTelecomunicacoes.controller;

import br.com.dbc.vemser.projetoTelecomunicacoes.documentacao.EnderecoControllerDoc;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.dto.EnderecoDTO;
import br.com.dbc.vemser.projetoTelecomunicacoes.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.projetoTelecomunicacoes.service.EnderecoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequestMapping("/endereco")
public class EnderecoController implements EnderecoControllerDoc {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/pessoa/{idPessoa}") // GET http://localhost:8080/endereco/pessoa/{idPessoa}
    public ResponseEntity<EnderecoDTO> listByIdPessoa(@PathVariable("idPessoa") Integer id) throws Exception {
        log.debug("Retornando endereço com idPessoa!");
        EnderecoDTO enderecoDTO = enderecoService.listByIdPessoa(id);
        log.debug("Retorno concluido com sucesso!");
        return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
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
}