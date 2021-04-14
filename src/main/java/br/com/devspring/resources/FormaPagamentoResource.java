package br.com.devspring.resources;

import br.com.devspring.error.CustomErrorType;
import br.com.devspring.domain.FormaPagamento;
import br.com.devspring.repository.FormaPagamentoRepository;
import br.com.devspring.services.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("formaspagamento")
public class FormaPagamentoResource {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping
    //@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(formaPagamentoService.get(), HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.GET, path = "/{id}")
    //busca informação passando um id. Ex:localhost:8080/formapagamento/2
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getFormaPagamentoById(@PathVariable("id") Long id) {
        Optional<FormaPagamento> formmaPagamento = formaPagamentoService.getPorID(id);
        if ((formmaPagamento.isEmpty()) || (formmaPagamento == null))
            return new ResponseEntity<>(new CustomErrorType("Forma Pagamento not found"), HttpStatus.NOT_FOUND); //retorna exceção personalizada
        return new ResponseEntity<>(formmaPagamento, HttpStatus.OK); //retorna a forma de pagamento.*/

    }

    /*@GetMapping(path = "/{name}")
    public ResponseEntity<?> getFormaPagamentoByName(@PathVariable("name") String name) {
        List<FormaPagamento> formmasPagamento = formaPagamentoService.getPorNome(name);
        if ((formmasPagamento.isEmpty())
            return new ResponseEntity<>(new CustomErrorType("Forma Pagamento not found"), HttpStatus.NOT_FOUND); //retorna exceção personalizada
        return new ResponseEntity<>(formmaPagamento, HttpStatus.OK); //retorna a forma de pagamento.*/

    //}

    @PostMapping
    //@RequestMapping(method = RequestMethod.POST) //inserir informação. Ex:localhost:8080/formapagamento
    public ResponseEntity<?> save(@RequestBody FormaPagamento formaPagamento) {
        return new ResponseEntity<>(formaPagamentoService.salvar(formaPagamento), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    //@RequestMapping(method = RequestMethod.DELETE) //deleta informação. Ex:localhost:8080/formapagamento ; passar no body o Jason
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        formaPagamentoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    //@RequestMapping(method = RequestMethod.PUT) //Altera informação. Ex:localhost:8080/formapagamento ; passar no body o Jason
    public ResponseEntity<?> update(@RequestBody FormaPagamento formaPagamento) {
        return new ResponseEntity<>(formaPagamentoService.salvar(formaPagamento), HttpStatus.OK);
    }
}
