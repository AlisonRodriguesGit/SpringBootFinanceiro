package br.com.devspring.resources;

import br.com.devspring.domain.MovimentacaoFinanceira;
import br.com.devspring.domain.Pedido;
import br.com.devspring.dto.MovimentacaoFinanceiraDTO;
import br.com.devspring.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @PreAuthorize("hasAnyRole('ADMIN')") //Somente se tiver permissão de ADMIN pode executar a requisição
    @GetMapping
    //@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<Pedido> pedidos = pedidoService.findAll(pageable);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping
    @RequestMapping(path = "/page")
    public ResponseEntity<Page<Pedido>> findPage(
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage",defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy",defaultValue = "data") String orderBy,
            @RequestParam(value = "direction",defaultValue = "DESC") String direction
    ){
        Page<Pedido> list = pedidoService.findPage(page,linesPerPage,orderBy,direction);
        //Page<MovimentacaoFinanceiraDTO> listDTO = list.stream().map(obj -> new MovimentacaoFinanceiraDTO(obj)).collect(Collectors.toList());
        //Page<MovimentacaoFinanceiraDTO> listDTO = list.map(obj -> new MovimentacaoFinanceiraDTO(obj));
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id){
        Pedido pedido = pedidoService.findById(id);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    //@Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Void> save(@Valid @RequestBody Pedido pedido) {
        pedido = pedidoService.save(pedido);
        //Pega a URI (Ex:localhost:8080/formaspagamento) e acrescenta /'numero id inserido' para retornar no Header do Reponse.
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
