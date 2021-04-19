package br.com.devspring.services;

import br.com.devspring.domain.FormaPagamento;
import br.com.devspring.repository.FormaPagamentoRepository;
import br.com.devspring.services.exception.DataIntegrityViolationException;
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

    private void verifyIfFormaPagamentoExist(Long id){
        if (formaPagamentoDAO.findById(id).isEmpty())
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + FormaPagamento.class.getName());
    }

    public Object findAll(Pageable pageable) {
        Iterable formaPagamento = formaPagamentoDAO.findAll(pageable);
        return formaPagamento;
    }

    public FormaPagamento findById(Long id){
        Optional<FormaPagamento> formaPagamento = formaPagamentoDAO.findById(id);
        return formaPagamento.orElseThrow(() -> new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + FormaPagamento.class.getName()));
    }

    public List<FormaPagamento> finByName(String nome){
        List<FormaPagamento> formasPagamento = formaPagamentoDAO.findByNameIgnoreCaseContaining(nome);
        return formasPagamento;
    }

    public FormaPagamento save(FormaPagamento formaPagamento){
        formaPagamento.setId(null);
        return formaPagamentoDAO.save(formaPagamento);
    }

    public FormaPagamento update(FormaPagamento formaPagamento){
        verifyIfFormaPagamentoExist(formaPagamento.getId());
        return formaPagamentoDAO.save(formaPagamento);
    }

    public void delete(Long id){
        verifyIfFormaPagamentoExist(id);
        try {
            formaPagamentoDAO.deleteById(id);
        }catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível excluir uma categoria que possui produtos");
        }
    }

}
