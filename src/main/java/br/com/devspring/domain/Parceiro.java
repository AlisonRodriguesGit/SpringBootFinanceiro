package br.com.devspring.domain;

import br.com.devspring.domain.enums.TipoParceiro;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Parceiro extends AbstractEntity {

    //@NotEmpty(message = "O campo nome do Parceiro é obrigatório")
    //@Length(min = 4, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String name;
    private String email;
    private String cpfOuCnpj;
    private Integer tipoParceiro;

    //Um parceiro tem vários endereços.
    @OneToMany(mappedBy = "parceiro")
    private List<Endereco> enderecos = new ArrayList<>();

    //Coleção do tipo Set não aceita repeticao de informacao;
    //Mapeamento para criar uma tabela simples, quando se tem somente um campo.
    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "parceiro")
    private List<MovimentacaoFinanceira> movimentacoesFinanceira = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "parceiro")
    private List<Pedido> pedidos = new ArrayList<>();

    public Parceiro() {
    }

    public Parceiro(String name, String email, String cpfOuCnpj, TipoParceiro tipoParceiro) {
        this.name = name;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipoParceiro = tipoParceiro.getCodigo();
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
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

    public TipoParceiro getTipoParceiro() {
        return TipoParceiro.toEnum(tipoParceiro);
    }

    public void setTipoParceiro(TipoParceiro tipoParceiro) {
        this.tipoParceiro = tipoParceiro.getCodigo();
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

    public List<MovimentacaoFinanceira> getMovimentacoesFinanceira() {
        return movimentacoesFinanceira;
    }

    public void setMovimentacoesFinanceira(List<MovimentacaoFinanceira> movimentacoesFinanceira) {
        this.movimentacoesFinanceira = movimentacoesFinanceira;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
