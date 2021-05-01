package br.com.devspring.repository;

import br.com.devspring.domain.FinanceiroUser;
import br.com.devspring.domain.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*Exemplo de utilização:
localhost:8080/formaspagamento?sort=name,asc&sort=xxxx,desc
localhost:8080/formaspagamento?page=1
 */
@Repository
//Trabalhar com paginação                       //Contem já alguns métodos de consulta. JpaRepository<FormaPagamento, Long>
public interface PedidoRepository extends JpaRepository<Pedido,Long> {//CrudRepository<FormaPagamento, Long> {

    @Transactional(readOnly = true)
    Page<Pedido> findByUser(FinanceiroUser user, Pageable pageable);
}
