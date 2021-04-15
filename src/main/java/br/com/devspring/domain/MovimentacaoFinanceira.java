package br.com.devspring.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class MovimentacaoFinanceira extends AbstractEntity{

    private String descricao;
    private Date dataLancamento;
    private Date dataPagamento;
    private Double valor;
    private Double valorPagamento;

    @JsonManagedReference//Trata referencia ciclica.
    @ManyToMany
    @JoinTable(name = "MOVFINANCEIRA_FORMAPAGAMENTO",
        joinColumns = @JoinColumn(name = "mov_financeira_id"),
        inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    public MovimentacaoFinanceira() {
    }

    public MovimentacaoFinanceira(String descricao, Date dataLancamento, Date dataPagamento, Double valor, Double valorPagamento) {
        this.descricao = descricao;
        this.dataLancamento = dataLancamento;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.valorPagamento = valorPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(Double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public List<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }
}
