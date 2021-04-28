package br.com.devspring.services;

import br.com.devspring.domain.Categoria;
import br.com.devspring.domain.Produto;
import br.com.devspring.repository.CategoriaRepository;
import br.com.devspring.repository.ProdutoRepository;
import br.com.devspring.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//mudanca
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Page<Produto> findAll(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    private void verifyIfPedidoExist(Long id){
        if (produtoRepository.findById(id).isEmpty()) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName());
        }
    }

    public Produto findById(Long id){
        Optional<Produto> pedido = produtoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public Page<Produto> search(String name, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.findDistinctByNameContainingAndAndCategoriaIn(name, categorias, PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy));
    }
}
