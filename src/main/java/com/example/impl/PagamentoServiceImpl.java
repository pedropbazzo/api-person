package com.example.impl;

import com.example.model.Pagamento;
import com.example.repository.PagamentoRepository;
import com.example.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoServiceImpl implements PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Override
    public Pagamento savePagamento(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    @Override
    public List<Pagamento> getAllPagamentos() {
        return pagamentoRepository.findAll();
    }
}

