package com.example.service;

import com.example.model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaService {
    List<Pessoa> getAll();
    Optional<Pessoa> getById(Integer id);
    List<Pessoa> findByNomeIgnoreCaseContainingOrderByNomeAsc(String nome);
    Pessoa create(Pessoa pessoa);
    Pessoa update(Integer id, Pessoa pessoa);
    void delete(Integer id);
    void deleteAll();
	Optional<Pessoa> getPessoa(Integer id);
}