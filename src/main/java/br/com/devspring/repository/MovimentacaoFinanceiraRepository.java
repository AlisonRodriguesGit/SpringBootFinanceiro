package br.com.devspring.repository;

import br.com.devspring.domain.MovimentacaoFinanceira;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoFinanceiraRepository extends PagingAndSortingRepository<MovimentacaoFinanceira,Long> {

}
