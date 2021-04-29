package br.com.devspring.resources;

import br.com.devspring.domain.FinanceiroUser;
import br.com.devspring.services.FinanceiroUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("user")
public class FinanceiroUserResource {

    @Autowired
    private FinanceiroUserService financeiroUserService;

    /*@ControllerAdvice
    public class CustomControllerAdvice {

        @InitBinder
        private void activateDirectFieldAccess(DataBinder dataBinder) {
            dataBinder.initDirectFieldAccess();
        }

    }*/

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable){
        Page<FinanceiroUser> financeiroUsers = financeiroUserService.findAll(pageable);
        return ResponseEntity.ok(financeiroUsers);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FinanceiroUser> findById(@PathVariable Long id){
        FinanceiroUser financeiroUser = financeiroUserService.findById(id);
        return ResponseEntity.ok(financeiroUser);
        //return ResponseEntity.ok().body(financeiroUser);
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody FinanceiroUser financeiroUser){
    //public ResponseEntity<Void> save(@RequestBody ParceiroNewDTO parceiroNewDTO){ // Se retornar somente a URI com id do Objeto Criado
        FinanceiroUser financeiroUser1 = financeiroUserService.save(financeiroUser);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(financeiroUser1.getId()).toUri();
        //return ResponseEntity.created(uri).build(); //Rertorna somente a Uri com id do Ojeto Criado
        //return new ResponseEntity<>(parceiro,HttpStatus.CREATED); //Rertorna somente o Objeto Criado
        return ResponseEntity.created(uri).body(financeiroUser1); ////Rertorna somente a Uri e o Objeto Criado;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> save(@PathVariable Long id, @Valid @RequestBody FinanceiroUser financeiroUser){
        financeiroUser.setId(id);
        financeiroUserService.update(financeiroUser);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        financeiroUserService.delete(id);
        return ResponseEntity.ok().build();
    }

}
