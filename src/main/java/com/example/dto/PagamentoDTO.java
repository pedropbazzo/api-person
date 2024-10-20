package com.example.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PagamentoDTO {
    private Long pessoaID;
    private BigDecimal valor;
    private String descricao;
    private String data;
    
    
    

    // Construtor padrão
    public PagamentoDTO() {}

    // Construtor com parâmetros
    public PagamentoDTO(Long pessoaID, BigDecimal valor, String descricao, String data) {
        this.pessoaID = pessoaID;
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
    }

    // Getters e Setters
    public Long getPessoaID() {
        return pessoaID;
    }

    public void setPessoaID(Long pessoaID) {
        this.pessoaID = pessoaID;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
