package br.com.devspring.resources;

import br.com.devspring.domain.Cliente;
import br.com.devspring.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok().body(cliente);
    }

}
