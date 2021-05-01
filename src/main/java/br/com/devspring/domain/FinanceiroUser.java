package br.com.devspring.domain;

import br.com.devspring.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class FinanceiroUser extends AbstractEntity {

    @NotEmpty
    @Column(unique = true)
    private String userName;
    @NotEmpty
    //@JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    @NotEmpty
    private String name;
    @Email
    @NotEmpty
    private String email;

    @ElementCollection(fetch = FetchType.EAGER) // FetchType.EAGER(Sempre trazer a lista de Perfis).
    @CollectionTable(name = "PERFIS") //Cria uma tabela simples (1:1) Ex: (Financeiro_User_ID ; PERFIS)
    private Set<Integer> perfis = new HashSet<>();

    //private boolean admin;

    public FinanceiroUser() {
        addPerfil(Perfil.USER);
    }

    public FinanceiroUser(String userName, String senha, String name, String email/*, boolean admin*/) {
        this.userName = userName;
        this.senha = senha;
        this.name = name;
        this.email = email;
        addPerfil(Perfil.USER);
        //this.admin = admin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public Set<Perfil> getPerfins() {
        //Percorre a lista de Perfil, convertendo o id para o Perfil e retorna uma Lista com os PERFIS.
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getCod());
    }

    /*public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }*/
}
