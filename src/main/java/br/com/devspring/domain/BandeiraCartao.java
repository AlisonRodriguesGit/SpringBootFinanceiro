package br.com.devspring.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BandeiraCartao extends AbstractEntity{

    @NotEmpty(message = "O campo nome da Bandeira do Cartao é obrigatório")
    @Length(min = 3, max = 50, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String name;

    @OneToMany(mappedBy = "bandeiraCartao")
    private List<FormaPagamentoCartao> formasPagamento = new ArrayList<>();
    //private List<FormaPagamento> formasPagamento = new ArrayList<>();

    public BandeiraCartao() {
    }

    public BandeiraCartao(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FormaPagamentoCartao> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<FormaPagamentoCartao> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }
}
