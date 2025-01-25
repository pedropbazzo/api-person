package com.example.controller;

import com.example.dto.PagamentoDTO;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	// Simula um banco de dados em memória para os pagamentos
	private Map<Long, PagamentoDTO> pagamentos = new HashMap<>();
	private Long contadorId = 1L;

	// Endpoint para criar um pagamento (POST)
	@PostMapping("/criar")
	public ResponseEntity<?> criarPagamento(@Valid @RequestBody PagamentoDTO pagamentoDTO, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getAllErrors());
		}

		// Salva o pagamento no "banco de dados" em memória
		pagamentoDTO.setPessoaID(contadorId);
		pagamentos.put(contadorId, pagamentoDTO);
		contadorId++;

		return ResponseEntity.ok(pagamentoDTO);
	}

	// Endpoint para obter um pagamento por ID (GET)
	@GetMapping("/{id}")
	public ResponseEntity<?> obterPagamento(@PathVariable Long id) {
		// Verifica se o pagamento existe no "banco de dados"
		PagamentoDTO pagamentoDTO = pagamentos.get(id);
		if (pagamentoDTO == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(pagamentoDTO);
	}
}