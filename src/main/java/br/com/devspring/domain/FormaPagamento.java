package br.com.devspring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

//Cria um campo adicional para o Sping identificar. Utilizado caso a classe fosse abstrata para identificar os objetos filhos.
//EX: pagamentoComBoleto e pagamentoComCartao.
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
//Adicionar @JsonTypeName("pagamentoComBoleo") , na classe filha.
//Criar classe de configuração JacksonConfig (Verificar documento - 03-operacoes-de-CRUD-e-casos-de-uso)

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //Cria somente uma tabela, caso JOINED cria uma tabela tbm para classe herdeira.
public class FormaPagamento extends AbstractEntity{

    @NotEmpty(message = "O campo nome da forma de pagamento é obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String name;

    @JsonIgnore//Trata referencia ciclica. //especificamente não convert o atributo em Json.
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
