package com.example.service;

import com.example.model.Pagamento;

import java.util.List;

public interface PagamentoService {
    Pagamento savePagamento(Pagamento pagamento);
    List<Pagamento> getAllPagamentos();
}


