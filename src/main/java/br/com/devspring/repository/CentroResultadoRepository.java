package br.com.devspring.repository;

import br.com.devspring.domain.CentroResultado;
import br.com.devspring.domain.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*Exemplo de utilização:
localhost:8080/formaspagamento?sort=name,asc&sort=xxxx,desc
localhost:8080/formaspagamento?page=1
 */
@Repository
//Trabalhar com paginação                       //Contem já alguns métodos de consulta. JpaRepository<FormaPagamento, Long>
public interface CentroResultadoRepository extends JpaRepository<CentroResultado,Long> {//CrudRepository<FormaPagamento, Long> {
}
