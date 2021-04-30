package br.com.devspring.services;

import br.com.devspring.domain.FinanceiroUser;
import br.com.devspring.repository.FinancerioUserRepository;
import br.com.devspring.security.UserSpringSercurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*UserDetailsService permite fazer uma busca pelo nome do usuário*/
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private FinancerioUserRepository financerioUserRepository;

    @Override //Recebi um usuário e Retorna um UserDetails de acordo com a especificação do Spring.
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //Recuperando o Usuário
        //ofNullable verifica se o retorno não é vazio.
        FinanceiroUser financeiroUser = Optional.ofNullable(financerioUserRepository.findByUserName(userName))
                //orElseThrow . Lança uma exceção se for vazio.
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        /*Necessário criar uma Lista de GrantedAuthority com as autorizações de cada Grupo de usuário.
          O exemplo abaixo poderia ser modificado com a criação de um relacionamento entre usuário e Grupos de usuários*/

        //List<GrantedAuthority> authoritiesListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        //List<GrantedAuthority> authoritiesListUser = AuthorityUtils.createAuthorityList("ROLE_USER");

        //Retorna um Usuário do Spring(UserDetails) com a sua Lista de GrantedAuthority.
        return new UserSpringSercurity(financeiroUser.getId(), financeiroUser.getEmail(), financeiroUser.getPassword(),
                    financeiroUser.getPerfins());
    }
}
