package br.com.devspring.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Pedido extends AbstractEntity{

    private Date data;
    @ManyToMany
    @JoinTable(name = "PEDIDO_FORMAPAGAMENTO",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parceiro_id")
    private Parceiro parceiro;

    //Conjunto Set, garante que não vai ter item repetido no mesmo pedido.
    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itensPedido = new HashSet<>();

    public Pedido(){ }

    public Pedido(Date data, Parceiro parceiro) {
        this.data = data;
        this.parceiro = parceiro;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Set<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(Set<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public List<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public Parceiro getParceiro() {
        return parceiro;
    }

    public void setParceiro(Parceiro parceiro) {
        this.parceiro = parceiro;
    }
}
