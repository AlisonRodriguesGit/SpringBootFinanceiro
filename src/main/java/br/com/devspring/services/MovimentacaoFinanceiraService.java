package br.com.devspring.services;

import br.com.devspring.domain.MovimentacaoFinanceira;
import br.com.devspring.domain.Parceiro;
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
    private MovimentacaoFinanceiraRepository movimentacaoFinanceiraRepository;

    public Page<MovimentacaoFinanceira> findAll(Pageable pageable){
        return movimentacaoFinanceiraRepository.findAll(pageable);
    }

    private void verifyIfMovimentacaoFinanceiraExist(Long id){
        if (movimentacaoFinanceiraRepository.findById(id).isEmpty()) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Parceiro.class.getName());
        }
    }

    public MovimentacaoFinanceira findById(Long id){
        Optional<MovimentacaoFinanceira> movimentacaoFinanceira = movimentacaoFinanceiraRepository.findById(id);
        return movimentacaoFinanceira.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + MovimentacaoFinanceira.class.getName()));
    }

    public Page<MovimentacaoFinanceira> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return movimentacaoFinanceiraRepository.findAll(pageRequest);
    }

    public MovimentacaoFinanceira save(MovimentacaoFinanceira movimentacaoFinanceira){
        movimentacaoFinanceira.setId(null);
        return movimentacaoFinanceiraRepository.save(movimentacaoFinanceira);
    }

    public MovimentacaoFinanceira fromDTO(MovimentacaoFinanceiraDTO dto){
        return new MovimentacaoFinanceira(dto.getParceiro(), dto.getDescricao(),dto.getDataLancamento(), dto.getDataPagamento(), dto.getValor(), dto.getValorPagamento(), dto.getTipoCusto());
    }

}
