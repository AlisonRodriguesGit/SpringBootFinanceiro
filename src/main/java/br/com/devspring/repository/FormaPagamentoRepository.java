package br.com.devspring.repository;

import br.com.devspring.domain.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository                                                    //Contem já alguns métodos de consulta
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>{ //extends CrudRepository {
    List<FormaPagamento> findByName(String nome);
}
