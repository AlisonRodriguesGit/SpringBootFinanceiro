package br.com.devspring.services;

import br.com.devspring.domain.MovimentacaoFinanceira;
import br.com.devspring.repository.MovimentacaoFinanceiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoFinanceiraService {

    @Autowired
    private MovimentacaoFinanceiraRepository movimentacaoFinanceiraDAO;

    public Object get(){
        return movimentacaoFinanceiraDAO.findAll();
        /*Iterable<MovimentacaoFinanceira> movimentacoesFinanceira =  movimentacaoFinanceiraDAO.findAll();
        return movimentacoesFinanceira;*/
    }
}
