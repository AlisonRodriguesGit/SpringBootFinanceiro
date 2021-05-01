package br.com.devspring.services;

import br.com.devspring.domain.FinanceiroUser;
import br.com.devspring.domain.Parceiro;
import br.com.devspring.domain.enums.Perfil;
import br.com.devspring.repository.FinancerioUserRepository;
import br.com.devspring.security.UserSpringSercurity;
import br.com.devspring.services.exception.AuthorizationException;
import br.com.devspring.services.exception.DataIntegrityViolationException;
import br.com.devspring.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinanceiroUserService {

    @Autowired
    private FinancerioUserRepository financeiroUserRepository;
    @Autowired
    private BCryptPasswordEncoder pe;

    //Retorna o usuário logado.
    public static UserSpringSercurity authenticated(){
        try {
            return (UserSpringSercurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return  null;
        }
    }

    public Page<FinanceiroUser> findAll(Pageable pageable) {
        return financeiroUserRepository.findAll(pageable);
    }

    private void verifyIfFinanceiroUserExist(Long id) {
        if (financeiroUserRepository.findById(id).isEmpty()) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Parceiro.class.getName());
        }
    }

    public FinanceiroUser findById(Long id) {
        //Recupera o usuário logado.
        UserSpringSercurity user = FinanceiroUserService.authenticated();

        //Se não tiver usuário logado ou não for ADMIN e o ID que está buscando não é dele mesmo, não pode acessar.
        // Ou seja, só pode acessar se for um ADMIN ou se tiver buscando por ele mesmo.
        if (user == null || !user.hasHole(Perfil.ADMIN) && !id.equals(user.getId())){
            throw new AuthorizationException("Acesso negado!");
        }

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
