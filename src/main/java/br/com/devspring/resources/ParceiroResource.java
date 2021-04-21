package br.com.devspring.resources;

import br.com.devspring.domain.Parceiro;
import br.com.devspring.services.ParceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("parceiros")
public class ParceiroResource {

    @Autowired
    private ParceiroService parceiroService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Parceiro> findById(@PathVariable Long id){
        Parceiro parceiro = parceiroService.findById(id);
        return ResponseEntity.ok().body(parceiro);
    }

}
