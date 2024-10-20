package com.example.impl;

import com.example.model.Pessoa;
import com.example.repository.PessoaRepository;
import com.example.service.PessoaService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Pessoa getPessoa(Integer id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    public Pessoa savePessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

	@Override
	public List<Pessoa> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Pessoa> getById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Pessoa> findByNomeIgnoreCaseContainingOrderByNomeAsc(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pessoa create(Pessoa pessoa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pessoa update(Integer id, Pessoa pessoa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
}
