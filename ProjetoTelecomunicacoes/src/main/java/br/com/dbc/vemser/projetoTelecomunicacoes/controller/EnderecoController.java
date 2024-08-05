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

    @GetMapping("/pessoa/{idPessoa}") // GET http://localhost:8080/endereco/pessoa/{idPessoa}
    public ResponseEntity<List<EnderecoDTO>> listByIdPessoaEndereco(@PathVariable("idPessoa") Integer id) throws Exception {
        log.debug("Retornando endereço com idPessoa!");
        List<EnderecoDTO> enderecoDTOList = enderecoService.listByIdPessoa(id);
        log.debug("Retorno concluido com sucesso!");
        return new ResponseEntity<>(enderecoDTOList, HttpStatus.OK);
    }

    @PostMapping() // POST http://localhost:8080/endereco/{idPessoa}
    public ResponseEntity<EnderecoDTO> createEndereco(@Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        log.debug("Criando endereço!");
        EnderecoDTO enderecoCriado = enderecoService.create(enderecoCreateDTO);
        log.debug("Criou endereço com sucesso!");
        return new ResponseEntity<>(enderecoCriado, HttpStatus.OK);
    }

    @PutMapping("/{id}") // PUT http://localhost:8080/endereco/{idPessoa}
    public ResponseEntity<EnderecoDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        log.debug("Atualizando endereço!");
        EnderecoDTO enderecoAtualizado = enderecoService.update(id, enderecoCreateDTO);
        log.debug("Atualizado com sucesso!");
        return new ResponseEntity<>(enderecoAtualizado, HttpStatus.OK);
    }
}