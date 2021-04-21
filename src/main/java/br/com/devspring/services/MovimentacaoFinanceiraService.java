package br.com.devspring.services;

import br.com.devspring.domain.FormaPagamento;
import br.com.devspring.domain.MovimentacaoFinanceira;
import br.com.devspring.dto.MovimentacaoFinanceiraDTO;
import br.com.devspring.repository.MovimentacaoFinanceiraRepository;
import br.com.devspring.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovimentacaoFinanceiraService {

    @Autowired
    private MovimentacaoFinanceiraRepository movimentacaoFinanceiraDAO;

    public Object findAll(){
        return movimentacaoFinanceiraDAO.findAll();
        /*Iterable<MovimentacaoFinanceira> movimentacoesFinanceira =  movimentacaoFinanceiraDAO.findAll();
        return movimentacoesFinanceira;*/
    }

    public MovimentacaoFinanceira findById(Long id){
        Optional<MovimentacaoFinanceira> movimentacaoFinanceira = movimentacaoFinanceiraDAO.findById(id);
        return movimentacaoFinanceira.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + MovimentacaoFinanceira.class.getName()));
    }

    public Page<MovimentacaoFinanceira> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return movimentacaoFinanceiraDAO.findAll(pageRequest);
    }

    public MovimentacaoFinanceira save(MovimentacaoFinanceira movimentacaoFinanceira){
        movimentacaoFinanceira.setId(null);
        return movimentacaoFinanceiraDAO.save(movimentacaoFinanceira);
    }

    public MovimentacaoFinanceira fromDTO(MovimentacaoFinanceiraDTO dto){
        return new MovimentacaoFinanceira(dto.getParceiro(), dto.getDescricao(),dto.getDataLancamento(), dto.getDataPagamento(), dto.getValor(), dto.getValorPagamento(), dto.getTipoCusto());
    }

}
