package br.com.devspring.services;

import br.com.devspring.domain.FormaPagamento;
import br.com.devspring.repository.FormaPagamentoRepository;
import br.com.devspring.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoDAO;

    public Object get() {
        Iterable formaPagamento = formaPagamentoDAO.findAll();
        return formaPagamento;
    }

    public FormaPagamento getPorID(Long id){
        Optional<FormaPagamento> formaPagamento = formaPagamentoDAO.findById(id);
        return formaPagamento.orElseThrow(() -> new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + FormaPagamento.class.getName()));
    }

    public List<FormaPagamento> getPorNome(String nome){
        List<FormaPagamento> formasPagamento = formaPagamentoDAO.findByName(nome);
        return formasPagamento;
    }

    public FormaPagamento salvar(FormaPagamento formaPagamento){
        FormaPagamento formaPagamento1  = formaPagamento;
        formaPagamentoDAO.save(formaPagamento1);
        return formaPagamento1;
    }

    public void deletar(Long id){
        formaPagamentoDAO.deleteById(id);
    }

}
