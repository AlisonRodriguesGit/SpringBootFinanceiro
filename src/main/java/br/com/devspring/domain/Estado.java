package br.com.devspring.domain;

public class Estado extends AbstractEntity {

    private String nome;

    public Estado(){

    }

    public Estado(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
