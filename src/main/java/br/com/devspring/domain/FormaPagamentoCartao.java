package br.com.devspring.domain;

import br.com.devspring.domain.enums.TipoLancamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FormaPagamentoCartao extends FormaPagamento{

    private Integer tipoLancamento;

    @ManyToOne
    @JoinColumn(name = "BANDEIRA_CARTAO_ID")
    private BandeiraCartao bandeiraCartao;

    public FormaPagamentoCartao(){
    }

    public FormaPagamentoCartao(String name, TipoLancamento tipoLancamento, BandeiraCartao bandeiraCartao) {
        super(name);
        this.tipoLancamento = tipoLancamento.getCodigo();
        this.bandeiraCartao = bandeiraCartao;
    }

    public TipoLancamento getTipoLancamento() {
        return TipoLancamento.toEnum(tipoLancamento);
    }

    public void setTipoLancamento(TipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento.getCodigo();
    }

    public BandeiraCartao getBandeiraCartao() {
        return bandeiraCartao;
    }

    public void setBandeiraCartao(BandeiraCartao bandeiraCartao) {
        this.bandeiraCartao = bandeiraCartao;
    }
}
