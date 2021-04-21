package br.com.devspring.domain;

import br.com.devspring.domain.enums.TipoCusto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class MovimentacaoFinanceira extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "parceiro_id")
    private Parceiro parceiro;
    private String descricao;
    private Date dataLancamento;
    private LocalDateTime dataPagamento;
    private Double valor;
    private Double valorPagamento;
    //@NotEmpty(message = "O campo tipo de custo é obrigatório")
    private Integer tipoCusto;

    @ManyToMany
    @JoinTable(name = "MOVFINANCEIRA_FORMAPAGAMENTO",
            joinColumns = @JoinColumn(name = "mov_financeira_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "MOVFINANCEIRA_CENTRORESULTADO",
            joinColumns = @JoinColumn(name = "mov_financeira_id"),
            inverseJoinColumns = @JoinColumn(name = "centro_resultado_id"))
    private List<CentroResultado> centrosResultado = new ArrayList<>();

    public MovimentacaoFinanceira() {
    }

    public MovimentacaoFinanceira(Parceiro parceiro,String descricao, Date dataLancamento, LocalDateTime dataPagamento, Double valor, Double valorPagamento, TipoCusto tipoCusto) {
        this.parceiro = parceiro;
        this.descricao = descricao;
        this.dataLancamento = dataLancamento;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.valorPagamento = valorPagamento;
        this.tipoCusto = tipoCusto.getCodigo();
    }

    public Parceiro getParceiro() {
        return parceiro;
    }

    public void setParceiro(Parceiro parceiro) {
        this.parceiro = parceiro;
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

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
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

    public TipoCusto getTipoCusto() {
        return TipoCusto.toEnum(tipoCusto);
    }

    public void setTipoCusto(TipoCusto tipoCusto) {
        this.tipoCusto = tipoCusto.getCodigo();
    }

    public List<CentroResultado> getCentrosResultado() {
        return centrosResultado;
    }

    public void setCentrosResultado(List<CentroResultado> centrosResultado) {
        this.centrosResultado = centrosResultado;
    }
}
