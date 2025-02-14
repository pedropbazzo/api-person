package com.example.impl;

import com.example.model.Pessoa;
import com.example.repository.PessoaRepository;
import com.example.service.PessoaService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class PessoaServiceImpl implements PessoaService {

	private PessoaRepository pessoaRepository;

	@Override
	public Optional<Pessoa> getById(Integer id) {
		return pessoaRepository.findById(id);
	}

	@Transactional
	public Pessoa savePessoa(@Valid @NotNull Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@Override
	public List<Pessoa> getAll() {
		return pessoaRepository.findAll();
	}

	@Override
	public List<Pessoa> findByNomeIgnoreCaseContainingOrderByNomeAsc(String nome) {
		return pessoaRepository.findByNomeIgnoreCaseContainingOrderByNomeAsc(nome);
	}

	@Override
	@Transactional
	public Pessoa create(@Valid @NotNull Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@Override
	@Transactional
	public Pessoa update(@NotNull Integer id, @Valid @NotNull Pessoa pessoa) {
		Pessoa existingPessoa = pessoaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Pessoa not found with id: " + id));

		existingPessoa.setNome(pessoa.getNome());
		existingPessoa.setCpf(pessoa.getCpf());
		existingPessoa.setDataNascimento(pessoa.getDataNascimento());
		existingPessoa.setEmail(pessoa.getEmail());
		existingPessoa.setNacionalidade(pessoa.getNacionalidade());
		existingPessoa.setNaturalidade(pessoa.getNaturalidade());
		existingPessoa.setSexo(pessoa.getSexo());

		return pessoaRepository.save(existingPessoa);
	}

	@Override
	@Transactional
	public void delete(@NotNull Integer id) {
		pessoaRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void deleteAll() {
		pessoaRepository.deleteAll();
	}
}
