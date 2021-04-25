package br.com.devspring.resources;

import br.com.devspring.domain.Pedido;
import br.com.devspring.domain.Produto;
import br.com.devspring.dto.ProdutoDTO;
import br.com.devspring.resources.utils.URL;
import br.com.devspring.services.PedidoService;
import br.com.devspring.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    //@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<Produto> produtos = produtoService.findAll(pageable);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id){
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping(value = "/page")
    //@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction ) {

        String nameDecoded = URL.decodeParam(name);
        List<Long> ids = URL.decodeLongList(categorias); //Convert String de categorias(1,2,3...) em uma lista de IDs do tipo Long.
        Page<Produto> produtos = produtoService.search(nameDecoded, ids, page, linesPerPage, orderBy, direction);
                                        //percore a page de produtos, adicionando em obj do tipo ProdutoDTO, retornando uma Page de ProdutoDTO.
        Page<ProdutoDTO> produtosDTO = produtos.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok(produtosDTO);
    }

}
