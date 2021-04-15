package br.com.devspring.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FormaPagamento extends AbstractEntity{

    private String name;

    @JsonBackReference//Trata referencia ciclica.
    @ManyToMany(mappedBy = "formasPagamento")
    private List<MovimentacaoFinanceira> movimetacoesFinanceira = new ArrayList<>();

    public FormaPagamento() {
    }

    public FormaPagamento(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMovimetacoesFinanceira(List<MovimentacaoFinanceira> movimetacoesFinanceira) {
        this.movimetacoesFinanceira = movimetacoesFinanceira;
    }

    public List<MovimentacaoFinanceira> getMovimetacoesFinanceira() {
        return movimetacoesFinanceira;
    }

}
