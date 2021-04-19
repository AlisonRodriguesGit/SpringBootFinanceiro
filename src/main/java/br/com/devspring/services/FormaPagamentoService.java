package br.com.devspring.services;

import br.com.devspring.domain.FormaPagamento;
import br.com.devspring.repository.FormaPagamentoRepository;
import br.com.devspring.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoDAO;

    private void verificaSeFormaPagamentoExiste(Long id){
        if (formaPagamentoDAO.findById(id).isEmpty())
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + FormaPagamento.class.getName());
    }

    public Object get(Pageable pageable) {
        Iterable formaPagamento = formaPagamentoDAO.findAll(pageable);
        return formaPagamento;
    }

    public FormaPagamento getPorID(Long id){
        Optional<FormaPagamento> formaPagamento = formaPagamentoDAO.findById(id);
        return formaPagamento.orElseThrow(() -> new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + FormaPagamento.class.getName()));
    }

    public List<FormaPagamento> getPorNome(String nome){
        List<FormaPagamento> formasPagamento = formaPagamentoDAO.findByNameIgnoreCaseContaining(nome);
        return formasPagamento;
    }

    public FormaPagamento salvar(FormaPagamento formaPagamento){
        //FormaPagamento formaPagamento1  = formaPagamento;
        formaPagamento.setId(null);
        return formaPagamentoDAO.save(formaPagamento);
        //return formaPagamento;
    }

    public void deletar(Long id){
        verificaSeFormaPagamentoExiste(id);
        formaPagamentoDAO.deleteById(id);
    }

}
