package br.com.devspring.resources;

import br.com.devspring.domain.Parceiro;
import br.com.devspring.dto.ParceiroDTO;
import br.com.devspring.dto.ParceiroNewDTO;
import br.com.devspring.dto.ParceiroUpdateDTO;
import br.com.devspring.services.ParceiroService;
import br.com.devspring.services.validation.ParceiroUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("parceiros")
public class ParceiroResource {

    @Autowired
    private ParceiroService parceiroService;

    @ControllerAdvice
    public class CustomControllerAdvice {

        @InitBinder
        private void activateDirectFieldAccess(DataBinder dataBinder) {
            dataBinder.initDirectFieldAccess();
        }

    }

    @PreAuthorize("hasAnyRole('ADMIN')") //Somente se tiver permissão de ADMIN pode executar a requisição
    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable){
        Page<Parceiro> parceiros = parceiroService.findAll(pageable);
        return ResponseEntity.ok(parceiros);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Parceiro> findById(@PathVariable Long id){
        Parceiro parceiro = parceiroService.findById(id);
        return ResponseEntity.ok(parceiro);
        //return ResponseEntity.ok().body(parceiro);
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ParceiroNewDTO parceiroNewDTO){
    //public ResponseEntity<Void> save(@RequestBody ParceiroNewDTO parceiroNewDTO){ // Se retornar somente a URI com id do Objeto Criado
        Parceiro parceiro = parceiroService.save(parceiroService.newDTOfromEntidade(parceiroNewDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(parceiro.getId()).toUri();
        ParceiroDTO parceiroDTO = parceiroService.fromDTO(parceiro);
        //return ResponseEntity.created(uri).build(); //Rertorna somente a Uri com id do Ojeto Criado
        //return new ResponseEntity<>(parceiro,HttpStatus.CREATED); //Rertorna somente o Objeto Criado
        return ResponseEntity.created(uri).body(parceiroDTO); ////Rertorna somente a Uri e o Objeto Criado;
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> save(@PathVariable Long id, @Valid @RequestBody ParceiroUpdateDTO parceiroDTO){
        Parceiro parceiro = parceiroService.updateDTOfromEntidade(parceiroDTO);
        parceiro.setId(id);
        parceiroService.update(parceiro);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')") //Somente se tiver permissão de ADMIN pode executar a requisição
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        parceiroService.delete(id);
        return ResponseEntity.ok().build();
    }

}
