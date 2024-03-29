package br.com.devspring.services;

import br.com.devspring.domain.FinanceiroUser;
import br.com.devspring.domain.ItemPedido;
import br.com.devspring.domain.MovimentacaoFinanceira;
import br.com.devspring.domain.Pedido;
import br.com.devspring.domain.enums.TipoCusto;
import br.com.devspring.repository.ItemPedidoRepository;
import br.com.devspring.repository.MovimentacaoFinanceiraRepository;
import br.com.devspring.repository.PedidoRepository;
import br.com.devspring.security.UserSpringSercurity;
import br.com.devspring.services.exception.AuthorizationException;
import br.com.devspring.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private MovimentacaoFinanceiraRepository movimentacaoFinanceiraRepository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private FinanceiroUserService financeiroUserService;

    public Page<Pedido> findAll(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    private void verifyIfPedidoExist(Long id){
        if (pedidoRepository.findById(id).isEmpty()) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName());
        }
    }

    public Pedido findById(Long id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido save(Pedido pedido){
        pedido.setId(null);
        pedido.setData(new Date());
        //pedido.getParceiro().setId(5L);
        for (ItemPedido itemPedido : pedido.getItensPedido()) {
            itemPedido.setDesconto(0.00);
            itemPedido.setPreco(produtoService.findById(itemPedido.getProduto().getId()).getValor());
            itemPedido.setPedido(pedido);
        }
        for (MovimentacaoFinanceira mov : pedido.getMovimentacoesFinanceira()) {
            mov.setDataLancamento(new Date());
            mov.setPedido(pedido);
            mov.setValor(pedido.getValorTotal());
            //mov.setTipoCusto(TipoCusto.VARIAVEL);
        }

        pedido = pedidoRepository.save(pedido);
        movimentacaoFinanceiraRepository.saveAll(pedido.getMovimentacoesFinanceira());
        itemPedidoRepository.saveAll(pedido.getItensPedido());
        return pedidoRepository.save(pedido);
    }

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        //pega o usuário logado
        UserSpringSercurity userSpringSercurity = FinanceiroUserService.authenticated();
        if (userSpringSercurity == null){
            throw new AuthorizationException("Acesso negado!");
        }
        //pedo o usuário do banco de dados.
        FinanceiroUser user = financeiroUserService.findById(userSpringSercurity.getId());

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return pedidoRepository.findByUser(user, pageRequest);
    }
}
