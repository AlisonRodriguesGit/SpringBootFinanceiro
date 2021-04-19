package br.com.devspring.dto;

import br.com.devspring.domain.AbstractEntity;
import br.com.devspring.domain.MovimentacaoFinanceira;

import java.util.Date;

public class MovimentacaoFinanceiraDTO  extends AbstractEntity{

    private String descricao;
    private Date dataLancamento;
    private Date dataPagamento;
    private Double valor;
    private Double valorPagamento;

    public MovimentacaoFinanceiraDTO() {
    }

    public MovimentacaoFinanceiraDTO(MovimentacaoFinanceira movimentacaoFinanceira) {
        descricao = movimentacaoFinanceira.getDescricao();
        dataLancamento = movimentacaoFinanceira.getDataLancamento();
        dataPagamento = movimentacaoFinanceira.getDataPagamento();
        valor = movimentacaoFinanceira.getValor();
        valorPagamento = movimentacaoFinanceira.getValorPagamento();
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
}
