package br.com.devspring.dto;

import br.com.devspring.domain.AbstractEntity;
import br.com.devspring.domain.Endereco;
import br.com.devspring.domain.MovimentacaoFinanceira;
import br.com.devspring.domain.Parceiro;
import br.com.devspring.domain.enums.TipoCusto;
import br.com.devspring.domain.enums.TipoParceiro;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class ParceiroDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento obrigatório")
    //@NotEmpty(message = "O campo nome do Parceiro é obrigatório")
    //@Length(min = 4, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String name;
    private String email;
    private String cpfOuCnpj;
    private Integer tipoParceiro;

    private List<Endereco> enderecos = new ArrayList<>();
    private Set<String> telefones = new HashSet<>();

    public ParceiroDTO() {
    }

    public ParceiroDTO(String name, String email, String cpfOuCnpj, TipoParceiro tipoParceiro) {
        this.name = name;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipoParceiro = tipoParceiro.getCodigo();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getTipoParceiro() {
        return tipoParceiro;
    }

    public void setTipoParceiro(Integer tipoParceiro) {
        this.tipoParceiro = tipoParceiro;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }
}
