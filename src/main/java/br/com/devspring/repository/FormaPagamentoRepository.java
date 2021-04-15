package br.com.devspring.repository;

import br.com.devspring.domain.FormaPagamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

//@Repository                                      //Trabalhar com paginação                       //Contem já alguns métodos de consulta. JpaRepository<FormaPagamento, Long>
public interface FormaPagamentoRepository extends PagingAndSortingRepository<FormaPagamento,Long>{//CrudRepository<FormaPagamento, Long> {
    List<FormaPagamento> findByNameIgnoreCaseContaining(String name);
}
