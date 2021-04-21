package br.com.devspring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CentroResultado extends AbstractEntity {

    @NotEmpty(message = "O campo nome da forma de pagamento é obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String name;

    @JsonIgnore//Trata referencia ciclica. //especificamente não convert o atributo em Json.
    @ManyToMany(mappedBy = "centrosResultado")
    private List<MovimentacaoFinanceira> movimetacoesFinanceira = new ArrayList<>();

    public CentroResultado(){
    }

    public  CentroResultado(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MovimentacaoFinanceira> getMovimetacoesFinanceira() {
        return movimetacoesFinanceira;
    }

    public void setMovimetacoesFinanceira(List<MovimentacaoFinanceira> movimetacoesFinanceira) {
        this.movimetacoesFinanceira = movimetacoesFinanceira;
    }
}
