package br.com.devspring.resources;

import br.com.devspring.domain.FormaPagamento;
import br.com.devspring.domain.Pedido;
import br.com.devspring.services.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("formaspagamento")
public class FormaPagamentoResource {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @PreAuthorize("hasAnyRole('ADMIN')") //Somente se tiver permissão de ADMIN pode executar a requisição
    @GetMapping
    //@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<FormaPagamento> formasPagamento = formaPagamentoService.findAll(pageable);
        return ResponseEntity.ok(formasPagamento);
    }

    //@RequestMapping(method = RequestMethod.GET, path = "/{id}")
    //busca informação passando um id. Ex:localhost:8080/formapagamento/2
    @GetMapping(path = "/{id}")
    public ResponseEntity<FormaPagamento> findById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDatails) { //@AuthenticationPrincipal UserDetails userDatails -> pega o usuário logado
        FormaPagamento formaPagamento = formaPagamentoService.findById(id);
        return ResponseEntity.ok(formaPagamento);
    }

    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity<List<FormaPagamento>> finByName(@PathVariable("name") String name) {
        List<FormaPagamento> formasPagamento =  formaPagamentoService.finByName(name);
        return ResponseEntity.ok().body(formasPagamento);
        //return new ResponseEntity<>(formaPagamentoService.getPorName(name), HttpStatus.OK);
    }


    //@Transactional(rollbackFor = Exception.class) //Por padrão faz roolback quando existe exceção que não é checked. Se for necessário informar explicitamente
    //@RequestMapping(method = RequestMethod.POST) //inserir informação. Ex:localhost:8080/formapagamento
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody FormaPagamento formaPagamento) {
        formaPagamentoService.save(formaPagamento);
                  //Pega a URI (Ex:localhost:8080/formaspagamento) e acrescenta /'numero id inserido' para retornar no Header do Reponse.
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(formaPagamento.getId()).toUri();
        return ResponseEntity.created(uri).build();

        //RETORNA O OBJETO.
        //return ResponseEntity.created().body(formaPagamento);
        //return new ResponseEntity<>(formaPagamentoService.salvar(formaPagamento), HttpStatus.CREATED); //201
    }

    //@RequestMapping(method = RequestMethod.DELETE) //deleta informação. Ex:localhost:8080/formapagamento ; passar no body o Jason
    @PreAuthorize("ADMIM") //Indica que para excluir precisa de permissão de Admin, configurado em 'SecurityConfig'.
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        formaPagamentoService.delete(id);
        return ResponseEntity.noContent().build();

        //return new ResponseEntity<>(HttpStatus.OK);//200 //Pode ser Status NO_CONTENT, precisa só seguir um padrão.
    }

    @PutMapping(path = "/{id}")
    //@RequestMapping(method = RequestMethod.PUT) //Altera informação. Ex:localhost:8080/formapagamento ; passar no body o Jason
    public ResponseEntity<Void> update(@RequestBody FormaPagamento formaPagamento, @PathVariable Long id) {
        formaPagamento.setId(id);
        formaPagamento = formaPagamentoService.update(formaPagamento);
        return ResponseEntity.noContent().build();

        //RETORNA O OBJETO.
        //return ResponseEntity.ok().body(formaPagamento);
        //return new ResponseEntity<>(formaPagamentoService.salvar(formaPagamento), HttpStatus.OK);//200 //Pode ser Status NO_CONTENT, precisa só seguir um padrão.
    }
}
