package br.com.devspring.services;

import br.com.devspring.domain.FinanceiroUser;
import br.com.devspring.domain.Parceiro;
import br.com.devspring.repository.FinancerioUserRepository;
import br.com.devspring.services.exception.DataIntegrityViolationException;
import br.com.devspring.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinanceiroUserService {

    @Autowired
    private FinancerioUserRepository financeiroUserRepository;
    @Autowired
    private BCryptPasswordEncoder pe;

    public Page<FinanceiroUser> findAll(Pageable pageable) {
        return financeiroUserRepository.findAll(pageable);
    }

    private void verifyIfFinanceiroUserExist(Long id) {
        if (financeiroUserRepository.findById(id).isEmpty()) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Parceiro.class.getName());
        }
    }

    public FinanceiroUser findById(Long id) {
        Optional<FinanceiroUser> financeiroUser = financeiroUserRepository.findById(id);
        return financeiroUser.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + FinanceiroUser.class.getName()));
    }

    public FinanceiroUser save(FinanceiroUser financeiroUser) {
        financeiroUser.setId(null);
        financeiroUser.setSenha(pe.encode(financeiroUser.getSenha()));
        return financeiroUserRepository.save(financeiroUser);
    }

    public FinanceiroUser update(FinanceiroUser financeiroUser) {
        verifyIfFinanceiroUserExist(financeiroUser.getId());
        return financeiroUserRepository.save(financeiroUser);
    }

    public void delete(Long id) {
        verifyIfFinanceiroUserExist(id);
        try {
            financeiroUserRepository.deleteById(id);
        }catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível excluir um parceiro que possui Pedidos");
        }
    }

}
