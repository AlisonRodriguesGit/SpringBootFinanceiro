package br.com.devspring.resources;

import br.com.devspring.domain.FormaPagamento;
import br.com.devspring.services.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> findById(@PathVariable Long id) {
        FormaPagamento formaPagamento = formaPagamentoService.getPorID(id);
        return ResponseEntity.ok().body(formaPagamento);
    }

    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity<?> finByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(formaPagamentoService.getPorNome(name), HttpStatus.OK);
    }

    @PostMapping
    //@RequestMapping(method = RequestMethod.POST) //inserir informação. Ex:localhost:8080/formapagamento
    public ResponseEntity<?> save(@RequestBody FormaPagamento formaPagamento) {
        return new ResponseEntity<>(formaPagamentoService.salvar(formaPagamento), HttpStatus.CREATED); //201
    }

    @DeleteMapping(path = "/{id}")
    //@RequestMapping(method = RequestMethod.DELETE) //deleta informação. Ex:localhost:8080/formapagamento ; passar no body o Jason
    public ResponseEntity<?> delete(@PathVariable Long id) {
        formaPagamentoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);//200 //Pode ser Status NO_CONTENT, precisa só seguir um padrão.
    }

    @PutMapping
    //@RequestMapping(method = RequestMethod.PUT) //Altera informação. Ex:localhost:8080/formapagamento ; passar no body o Jason
    public ResponseEntity<?> update(@RequestBody FormaPagamento formaPagamento) {
        return new ResponseEntity<>(formaPagamentoService.salvar(formaPagamento), HttpStatus.OK);//200 //Pode ser Status NO_CONTENT, precisa só seguir um padrão.
    }
}
