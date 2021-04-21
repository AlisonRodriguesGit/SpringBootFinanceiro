package br.com.devspring.resources;

import br.com.devspring.domain.FormaPagamento;
import br.com.devspring.domain.MovimentacaoFinanceira;
import br.com.devspring.dto.MovimentacaoFinanceiraDTO;
import br.com.devspring.services.MovimentacaoFinanceiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("movimentacaoFinanceira")
public class MovimentacaoFinanceiraResouce {

    @Autowired
    private MovimentacaoFinanceiraService movimentacaoFinanceiraService;

    @GetMapping
    public ResponseEntity<Object> findAll(Pageable pageable){
        //List<MovimentacaoFinanceira> list = movimentacaoFinanceiraService.findAll();
        //List<MovimentacaoFinanceiraDTO> listDTO = list.stream().map(obj -> new MovimentacaoFinanceiraDTO(obj)).collect(Collectors.toList());
        //return ResponseEntity.ok().body(listDTO);

        Page<MovimentacaoFinanceira> movimentacoesFinanceira = movimentacaoFinanceiraService.findAll(pageable);
        return ResponseEntity.ok(movimentacoesFinanceira);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MovimentacaoFinanceira> listByID(@PathVariable Long id){
        MovimentacaoFinanceira movimentacaoFinanceira = movimentacaoFinanceiraService.findById(id);
        return ResponseEntity.ok().body(movimentacaoFinanceira);
    }


    //localhost:8080/movimentacaoFinanceira/page?linesPerPage=1&page=2
    @GetMapping
    @RequestMapping(path = "/page")
    public ResponseEntity<Page<MovimentacaoFinanceiraDTO>> findPage(
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage",defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy",defaultValue = "dataLancamento") String orderBy,
            @RequestParam(value = "direction",defaultValue = "ASC") String direction
    ){
        Page<MovimentacaoFinanceira> list = movimentacaoFinanceiraService.findPage(page,linesPerPage,orderBy,direction);
        //Page<MovimentacaoFinanceiraDTO> listDTO = list.stream().map(obj -> new MovimentacaoFinanceiraDTO(obj)).collect(Collectors.toList());
        Page<MovimentacaoFinanceiraDTO> listDTO = list.map(obj -> new MovimentacaoFinanceiraDTO(obj));
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    //@Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Void> save(@Valid @RequestBody MovimentacaoFinanceiraDTO movimentacaoFinanceiraDTO) {
        MovimentacaoFinanceira movimentacaoFinanceira = movimentacaoFinanceiraService.fromDTO(movimentacaoFinanceiraDTO);
        movimentacaoFinanceiraService.save(movimentacaoFinanceira);
        //Pega a URI (Ex:localhost:8080/formaspagamento) e acrescenta /'numero id inserido' para retornar no Header do Reponse.
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(movimentacaoFinanceiraDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
