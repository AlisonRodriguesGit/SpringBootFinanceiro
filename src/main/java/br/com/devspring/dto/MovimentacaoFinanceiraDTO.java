package br.com.devspring.dto;

import br.com.devspring.domain.AbstractEntity;
import br.com.devspring.domain.MovimentacaoFinanceira;
import br.com.devspring.domain.Parceiro;
import br.com.devspring.domain.enums.TipoCusto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;

public class MovimentacaoFinanceiraDTO  extends AbstractEntity{

    @NotEmpty(message = "Preenchimento obrigatório")
    private Parceiro parceiro;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String descricao;
    private Date dataLancamento;
    private LocalDateTime dataPagamento;
    private Double valor;
    private Double valorPagamento;
    private Integer tipoCusto;

    public MovimentacaoFinanceiraDTO() {
    }

    public MovimentacaoFinanceiraDTO(MovimentacaoFinanceira movimentacaoFinanceira) {
        parceiro = movimentacaoFinanceira.getParceiro();
        descricao = movimentacaoFinanceira.getDescricao();
        dataLancamento = movimentacaoFinanceira.getDataLancamento();
        dataPagamento = movimentacaoFinanceira.getDataPagamento();
        valor = movimentacaoFinanceira.getValor();
        valorPagamento = movimentacaoFinanceira.getValorPagamento();
        tipoCusto = movimentacaoFinanceira.getTipoCusto().getCodigo();
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

    public TipoCusto getTipoCusto() {
        return TipoCusto.toEnum(tipoCusto);
    }

    public void setTipoCusto(TipoCusto tipoCusto) {
        this.tipoCusto = tipoCusto.getCodigo();
    }
}
