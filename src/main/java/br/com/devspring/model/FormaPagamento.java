package br.com.devspring.model;

public class FormaPagamento {

    public String descricao;

    public FormaPagamento(String descricao) {
        this.descricao = descricao;
    }

    public FormaPagamento() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
