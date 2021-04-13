package br.com.devspring.resources;

import br.com.devspring.model.FormaPagamento;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("formapagamento")
public class FormaPagamentoResource {

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public List<FormaPagamento> listAll() {
        return asList(new FormaPagamento("Dinheiro"), new FormaPagamento("Cartão"));
    }
}
