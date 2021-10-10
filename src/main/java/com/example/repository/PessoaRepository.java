package com.example.repository;

import com.example.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    List<Pessoa> findByNomeIgnoreCaseContainingOrderByNomeAsc(String nome);
}
