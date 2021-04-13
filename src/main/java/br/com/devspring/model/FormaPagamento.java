package br.com.devspring.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

public class FormaPagamento {

    private int id;
    private String name;
    public static List<FormaPagamento> formaPagamentoList;

    static {
        studentRepository();
    }

    public FormaPagamento(int id, String name) {
        this(name);
        this.id = id;
    }

    public FormaPagamento(String name) {
        this.name = name;
    }

    public FormaPagamento() {
    }

    private static void studentRepository(){
        formaPagamentoList = new ArrayList<>(asList(new FormaPagamento(1,"Dinheiro"))); //, new FormaPagamento(2,"Cartão")
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormaPagamento that = (FormaPagamento) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
