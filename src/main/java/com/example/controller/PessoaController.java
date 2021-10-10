package com.example.controller;

import com.example.model.Pessoa;
import com.example.repository.PessoaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= "*")
@Api(value = "Pessoas")
@RestController
@RequestMapping({"/pessoas"})
public class PessoaController {

    @Autowired
    private PessoaRepository repository;

    @ApiOperation(value = "Lista todas as pessoas cadastradas")
    @GetMapping
    public List<Pessoa> getAll() {
        return repository.findAll();
    }

    @ApiOperation(value = "Lista uma pessoa cadastrada atraves do seu identificador(ID)")
    @GetMapping(path = {"/{id}"})
    public ResponseEntity getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Lista uma ou mais pessoas cadastradas buscando por nome(obrigatorio)")
    @GetMapping(path = "/findByName/{nome}")
    public ResponseEntity<?> findByNomeIgnoreCaseContainingOrderByNomeAsc(@PathVariable String nome){
        return new ResponseEntity<>(repository.findByNomeIgnoreCaseContainingOrderByNomeAsc(nome), HttpStatus.OK);
    }

    @ApiOperation(value = "Adiciona uma nova pessoa")
    @PostMapping
    public Pessoa create(@RequestBody Pessoa pessoa) {
        return repository.save(pessoa);
    }

    @ApiOperation(value = "Altera uma pessoa ja cadastrada")
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
                                 @RequestBody Pessoa pessoa) {
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

    @ApiOperation(value = "Remove uma pessoa cadastrada atraves do seu identificador(ID)")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Remove todas as pessoas cadastradas")
    @DeleteMapping(path = "/deleteAll")
    public ResponseEntity<?> deleteAll(){
        repository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
