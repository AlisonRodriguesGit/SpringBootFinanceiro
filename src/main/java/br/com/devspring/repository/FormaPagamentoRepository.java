package br.com.devspring.repository;

import br.com.devspring.domain.FormaPagamento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//@Repository                                                    //Contem já alguns métodos de consulta. JpaRepository<FormaPagamento, Long>
public interface FormaPagamentoRepository extends CrudRepository<FormaPagamento, Long> {
    List<FormaPagamento> findByNameIgnoreCaseContaining(String name);
}
