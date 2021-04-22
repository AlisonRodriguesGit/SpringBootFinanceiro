package br.com.devspring.dto;

import br.com.devspring.domain.Endereco;
import br.com.devspring.domain.enums.TipoParceiro;
import br.com.devspring.services.validation.ParceiroUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ParceiroUpdate
public class ParceiroUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 4, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String name;
    @NotEmpty(message = "O campo é obrigatório")
    @Email(message = "Email Inválido")
    private String email;
    @NotEmpty(message = "O campo é obrigatório")
    private String cpfOuCnpj;
    private Integer tipoParceiro;

    public ParceiroUpdateDTO() {
    }

    public ParceiroUpdateDTO(Long id, String name, String email, String cpfOuCnpj, TipoParceiro tipoParceiro) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipoParceiro = tipoParceiro.getCodigo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
