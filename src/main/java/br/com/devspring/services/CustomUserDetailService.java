package br.com.devspring.services;

import br.com.devspring.domain.FinanceiroUser;
import br.com.devspring.repository.FinancerioUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private FinancerioUserRepository financerioUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //Recuperando o Usuário
        //ofNullable verifica se o retorno não é vazio.
        FinanceiroUser financeiroUser = Optional.ofNullable(financerioUserRepository.findByUserName(userName))
                //orElseThrow . Lança uma exceção se for vazio.
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        /*Necessário criar uma Lista de GrantedAuthority com as autorizações de cada Grupo de usuário.
          O exemplo abaixo poderia ser modificado com a criação de um relacionamento entre usuário e Grupos de usuários*/
        List<GrantedAuthority> authoritiesListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        List<GrantedAuthority> authoritiesListUser = AuthorityUtils.createAuthorityList("ROLE_USER");

        //Retorna um Usuário do Spring(UserDetails) com a sua Lista de GrantedAuthority.
        return new User(financeiroUser.getUserName(), financeiroUser.getPassword(),
                    financeiroUser.isAdmin() ? authoritiesListAdmin: authoritiesListUser);
    }
}
