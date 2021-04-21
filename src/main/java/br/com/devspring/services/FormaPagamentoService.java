package br.com.devspring.services;

import br.com.devspring.domain.FormaPagamento;
import br.com.devspring.repository.FormaPagamentoRepository;
import br.com.devspring.services.exception.DataIntegrityViolationException;
import br.com.devspring.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;


    public Page<FormaPagamento> findAll(Pageable pageable) {
        return formaPagamentoRepository.findAll(pageable);
    }

    private void verifyIfFormaPagamentoExist(Long id){
        if (formaPagamentoRepository.findById(id).isEmpty())
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + FormaPagamento.class.getName());
    }

    public FormaPagamento findById(Long id){
        Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(id);
        return formaPagamento.orElseThrow(() -> new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + FormaPagamento.class.getName()));
    }

    public List<FormaPagamento> finByName(String nome){
        List<FormaPagamento> formasPagamento = formaPagamentoRepository.findByNameIgnoreCaseContaining(nome);
        return formasPagamento;
    }

    public FormaPagamento save(FormaPagamento formaPagamento){
        formaPagamento.setId(null);
        return formaPagamentoRepository.save(formaPagamento);
    }

    public FormaPagamento update(FormaPagamento formaPagamento){
        verifyIfFormaPagamentoExist(formaPagamento.getId());
        return formaPagamentoRepository.save(formaPagamento);
    }

    public void delete(Long id){
        verifyIfFormaPagamentoExist(id);
        try {
            formaPagamentoRepository.deleteById(id);
        }catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível excluir uma categoria que possui produtos");
        }
    }

}
