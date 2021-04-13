package br.com.devspring.resources;

import br.com.devspring.error.CustomErrorType;
import br.com.devspring.model.FormaPagamento;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("formaspagamento")
public class FormaPagamentoResource {

    @GetMapping
    //@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(FormaPagamento.formaPagamentoList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    //@RequestMapping(method = RequestMethod.GET, path = "/{id}")
    //busca informação passando um id. Ex:localhost:8080/formapagamento/2
    public ResponseEntity<?> getFormaPagamentoById(@PathVariable("id") int id) {
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setId(id);
        int index = FormaPagamento.formaPagamentoList.indexOf(formaPagamento); //buscando o indicce da lista

        if (index == -1)
            return new ResponseEntity<>(new CustomErrorType("Forma Pagamento not found"), HttpStatus.NOT_FOUND); //retorna exceção personalizada
        return new ResponseEntity<>(FormaPagamento.formaPagamentoList.get(index), HttpStatus.OK); //retorna a forma de pagamento.

    }

    @PostMapping
    //@RequestMapping(method = RequestMethod.POST) //inserir informação. Ex:localhost:8080/formapagamento
    public ResponseEntity<?> save(@RequestBody FormaPagamento formaPagamento) {
        FormaPagamento.formaPagamentoList.add(formaPagamento);
        return new ResponseEntity<>(formaPagamento, HttpStatus.OK);
    }

    @DeleteMapping
    //@RequestMapping(method = RequestMethod.DELETE) //deleta informação. Ex:localhost:8080/formapagamento ; passar no body o Jason
    public ResponseEntity<?> delete(@RequestBody FormaPagamento formaPagamento) {
        FormaPagamento.formaPagamentoList.remove(formaPagamento);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    //@RequestMapping(method = RequestMethod.PUT) //Altera informação. Ex:localhost:8080/formapagamento ; passar no body o Jason
    public ResponseEntity<?> update(@RequestBody FormaPagamento formaPagamento) {
        FormaPagamento.formaPagamentoList.remove(formaPagamento);
        FormaPagamento.formaPagamentoList.add(formaPagamento);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
