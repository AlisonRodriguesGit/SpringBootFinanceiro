package br.com.devspring.services;

import br.com.devspring.domain.FormaPagamento;
import br.com.devspring.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoDAO;

    public Object getFormasPagamento() {
        Iterable formaPagamento = formaPagamentoDAO.findAll();
        return formaPagamento;
    }

    public Optional<FormaPagamento> getFormasPagamentoPorID(Long id){
        Optional<FormaPagamento> formaPagamento = formaPagamentoDAO.findById(id);
        return formaPagamento;
    }

    public List<FormaPagamento> getFormasPagamentoPorNome(String nome){
        List<FormaPagamento> formasPagamento = formaPagamentoDAO.findByName(nome);
        return formasPagamento;
    }
}
