package br.com.devspring.repository;

import br.com.devspring.domain.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*Exemplo de utilização:
localhost:8080/formaspagamento?sort=name,asc&sort=xxxx,desc
localhost:8080/formaspagamento?page=1
 */
@Repository
//Trabalhar com paginação                       //Contem já alguns métodos de consulta. JpaRepository<FormaPagamento, Long>
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento,Long> {//CrudRepository<FormaPagamento, Long> {
    List<FormaPagamento> findByNameIgnoreCaseContaining(String name);
}
