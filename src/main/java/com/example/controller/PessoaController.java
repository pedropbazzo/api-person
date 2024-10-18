package com.example.controller;

import com.example.model.Pessoa;
import com.example.repository.PessoaRepository;
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
    private PessoaRepository repository;

    @ApiOperation(value = "Lista todas as pessoas cadastradas")
    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll() {
        List<Pessoa> pessoas = repository.findAll();
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

    @ApiOperation(value = "Lista uma pessoa cadastrada através do seu identificador (ID)")
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Lista uma ou mais pessoas cadastradas buscando por nome (obrigatório)")
    @GetMapping("/findByName/{nome}")
    public ResponseEntity<List<Pessoa>> findByNomeIgnoreCaseContainingOrderByNomeAsc(@PathVariable String nome) {
        List<Pessoa> pessoas = repository.findByNomeIgnoreCaseContainingOrderByNomeAsc(nome);
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

    @ApiOperation(value = "Adiciona uma nova pessoa")
    @PostMapping
    public ResponseEntity<Pessoa> create(@Valid @RequestBody Pessoa pessoa) {
        Pessoa savedPessoa = repository.save(pessoa);
        return new ResponseEntity<>(savedPessoa, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Altera uma pessoa já cadastrada")
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Integer id, @Valid @RequestBody Pessoa pessoa) {
        return repository.findById(id)
                .map(p -> {
                    p.setNome(pessoa.getNome());
                    p.setSexo(pessoa.getSexo());
                    p.setEmail(pessoa.getEmail());
                    p.setDataNascimento(pessoa.getDataNascimento());
                    p.setNaturalidade(pessoa.getNaturalidade());
                    p.setNacionalidade(pessoa.getNacionalidade());
                    p.setCpf(pessoa.getCpf());

                    Pessoa pessoaUpdated = repository.save(p);
                    return ResponseEntity.ok().body(pessoaUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Remove uma pessoa cadastrada através do seu identificador (ID)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Remove todas as pessoas cadastradas")
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAll() {
        repository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}