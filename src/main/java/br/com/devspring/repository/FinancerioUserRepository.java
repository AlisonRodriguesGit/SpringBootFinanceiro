package br.com.devspring.repository;

import br.com.devspring.domain.FinanceiroUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancerioUserRepository extends JpaRepository<FinanceiroUser,Long> {
    FinanceiroUser findByUserName(String username);
}
