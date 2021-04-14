package br.com.devspring.domain;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

@Entity
public class FormaPagamento extends AbstractEntity{

    private String name;

    public FormaPagamento() {
    }

    public FormaPagamento(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
