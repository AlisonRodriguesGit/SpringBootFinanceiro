package br.com.devspring.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.*;

@Entity
public class Pedido extends AbstractEntity{

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date data;

    @ManyToOne
    @JoinColumn(name = "parceiro_id")
    private Parceiro parceiro;

    //Conjunto Set, garante que não vai ter item repetido no mesmo pedido.
    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itensPedido = new HashSet<>();

    @OneToMany(mappedBy = "pedido")
    private List<MovimentacaoFinanceira> movimentacoesFinanceira = new ArrayList<>();

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

    public Parceiro getParceiro() {
        return parceiro;
    }

    public void setParceiro(Parceiro parceiro) {
        this.parceiro = parceiro;
    }

    public List<MovimentacaoFinanceira> getMovimentacoesFinanceira() {
        return movimentacoesFinanceira;
    }

    public void setMovimentacoesFinanceira(List<MovimentacaoFinanceira> movimentacoesFinanceira) {
        this.movimentacoesFinanceira = movimentacoesFinanceira;
    }

    public double getValorTotal(){
        double valorTotal = 0.00;
        for (ItemPedido itemPedido: getItensPedido()) {
            valorTotal += itemPedido.getSubTotal();
        }
        return valorTotal;
    }
}
