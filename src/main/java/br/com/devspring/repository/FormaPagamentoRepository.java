package br.com.devspring.repository;

import br.com.devspring.domain.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository                                                    //Contem já alguns métodos de consulta. JpaRepository<FormaPagamento, Long>
public interface FormaPagamentoRepository extends CrudRepository<FormaPagamento, Long> {
    List<FormaPagamento> findByNameIgnoreCaseContaining(String name);
}
