package br.com.devspring.repository;

import br.com.devspring.domain.MovimentacaoFinanceira;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovimentacaoFinanceiraRepository extends CrudRepository<MovimentacaoFinanceira,Long> {

}
