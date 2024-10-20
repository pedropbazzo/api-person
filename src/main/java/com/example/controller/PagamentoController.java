package com.example.controller;

import com.example.dto.PagamentoDTO;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	@PostMapping("/criar")
	public ResponseEntity<?> criarPagamento(@Valid @RequestBody PagamentoDTO pagamentoDTO, BindingResult result) {
	    if (result.hasErrors()) {
	        return ResponseEntity.badRequest().body(result.getAllErrors());
	    }
	    	    return ResponseEntity.ok(pagamentoDTO);
	}
}