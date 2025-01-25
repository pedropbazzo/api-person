package com.example.controller;

import com.example.model.Pessoa;
import com.example.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@Api(value = "Pessoas")
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @ApiOperation(value = "Lista todas as pessoas cadastradas")
    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll() {
        List<Pessoa> pessoas = service.getAll();
        return ResponseEntity.ok(pessoas);
    }

    @ApiOperation(value = "Lista uma pessoa cadastrada através do seu identificador (ID)")
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getById(@PathVariable Integer id) {
        return service.getById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Lista uma ou mais pessoas cadastradas buscando por nome (obrigatório)")
    @GetMapping("/findByName/{nome}")
    public ResponseEntity<List<Pessoa>> findByNome(@PathVariable String nome) {
        List<Pessoa> pessoas = service.findByNomeIgnoreCaseContainingOrderByNomeAsc(nome);
        return ResponseEntity.ok(pessoas);
    }

    @ApiOperation(value = "Adiciona uma nova pessoa")
    @PostMapping
    public ResponseEntity<Pessoa> create(@Valid @RequestBody Pessoa pessoa) {
        Pessoa savedPessoa = service.create(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPessoa);
    }

    @ApiOperation(value = "Altera uma pessoa já cadastrada")
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Integer id, @Valid @RequestBody Pessoa pessoa) {
        Pessoa updatedPessoa = service.update(id, pessoa);
        return ResponseEntity.ok(updatedPessoa);
    }

    @ApiOperation(value = "Remove uma pessoa cadastrada através do seu identificador (ID)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Remove todas as pessoas cadastradas")
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }
}