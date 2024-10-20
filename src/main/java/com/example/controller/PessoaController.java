package com.example.controller;

import com.example.model.Pessoa;
import com.example.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PessoaService service;

    @ApiOperation(value = "Lista todas as pessoas cadastradas")
    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll() {
        List<Pessoa> pessoas = service.getAll();
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

    @ApiOperation(value = "Lista uma pessoa cadastrada através do seu identificador (ID)")
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getById(@PathVariable Integer id) {
        return service.getById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Lista uma ou mais pessoas cadastradas buscando por nome (obrigatório)")
    @GetMapping("/findByName/{nome}")
    public ResponseEntity<List<Pessoa>> findByNomeIgnoreCaseContainingOrderByNomeAsc(@PathVariable String nome) {
        List<Pessoa> pessoas = service.findByNomeIgnoreCaseContainingOrderByNomeAsc(nome);
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

    @ApiOperation(value = "Adiciona uma nova pessoa")
    @PostMapping
    public ResponseEntity<Pessoa> create(@Valid @RequestBody Pessoa pessoa) {
        Pessoa savedPessoa = service.create(pessoa);
        return new ResponseEntity<>(savedPessoa, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Altera uma pessoa já cadastrada")
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Integer id, @Valid @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok().body(service.update(id, pessoa));
    }

    @ApiOperation(value = "Remove uma pessoa cadastrada através do seu identificador (ID)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Remove todas as pessoas cadastradas")
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}