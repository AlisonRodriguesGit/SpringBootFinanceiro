package br.com.devspring.repository;

import br.com.devspring.domain.FinanceiroUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancerioUserRepository extends PagingAndSortingRepository<FinanceiroUser,Long> {
    FinanceiroUser findByUserName(String username);
}
