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
		return pessoaRepository.findAll();
	}

	@Override
	public Optional<Pessoa> getById(Integer id) {
		return pessoaRepository.findById(id);
	}

	@Override
	public List<Pessoa> findByNomeIgnoreCaseContainingOrderByNomeAsc(String nome) {
		return pessoaRepository.findByNomeIgnoreCaseContainingOrderByNomeAsc(nome);
	}

	@Override
	public Pessoa create(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@Override
	public Pessoa update(Integer id, Pessoa pessoa) {
		return pessoaRepository.findById(id).map(existingPessoa -> {
			existingPessoa.setNome(pessoa.getNome());
			existingPessoa.setCpf(pessoa.getCpf());
			existingPessoa.setDataNascimento(pessoa.getDataNascimento());
			existingPessoa.setEmail(pessoa.getEmail());
			existingPessoa.setId(pessoa.getId());
			existingPessoa.setNacionalidade(pessoa.getNacionalidade());
			existingPessoa.setNaturalidade(pessoa.getNaturalidade());
			existingPessoa.setSexo(pessoa.getSexo());
			return pessoaRepository.save(existingPessoa);
		}).orElseThrow(() -> new IllegalArgumentException("Pessoa not found with id: " + id));
	}

	@Override
	public void delete(Integer id) {
		pessoaRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		pessoaRepository.deleteAll();
	}
}