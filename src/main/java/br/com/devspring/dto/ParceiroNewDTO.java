package br.com.devspring.dto;

import br.com.devspring.services.validation.ParceiroInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ParceiroInsert
public class ParceiroNewDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "O campo nome do Parceiro é obrigatório")
    @Length(min = 4, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String name;
    @NotEmpty(message = "O campo é obrigatório")
    @Email(message = "Email Inválido")
    private String email;
    @NotEmpty(message = "O campo é obrigatório")
    private String cpfOuCnpj;
    private Integer tipoParceiro;

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    @NotEmpty(message = "O campo é obrigatório")
    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Long cidadeID;

    public ParceiroNewDTO(){

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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public Long getCidadeID() {
        return cidadeID;
    }

    public void setCidadeID(Long cidadeID) {
        this.cidadeID = cidadeID;
    }


}
