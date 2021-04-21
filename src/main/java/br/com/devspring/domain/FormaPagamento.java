package br.com.devspring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //Cria somente uma tabela, caso JOINED cria uma tabela tbm para classe herdeira.
public class FormaPagamento extends AbstractEntity{

    @NotEmpty(message = "O campo nome da forma de pagamento é obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String name;

    @JsonIgnore//Trata referencia ciclica. //especificamente não convert o atributo em Json.
    @ManyToMany(mappedBy = "formasPagamento")
    private List<MovimentacaoFinanceira> movimetacoesFinanceira = new ArrayList<>();

    /*@JsonIgnore
    @ManyToMany(mappedBy = "formasPagamento")
    private List<Pedido> pedidos = new ArrayList<>();*/

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

   /* public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }*/

}
