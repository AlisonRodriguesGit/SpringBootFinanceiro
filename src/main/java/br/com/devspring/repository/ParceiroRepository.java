package br.com.devspring.repository;

import br.com.devspring.domain.Parceiro;
import br.com.devspring.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*Exemplo de utilização:
localhost:8080/formaspagamento?sort=name,asc&sort=xxxx,desc
localhost:8080/formaspagamento?page=1
 */
@Repository
//Trabalhar com paginação                       //Contem já alguns métodos de consulta. JpaRepository<FormaPagamento, Long>
public interface ParceiroRepository extends JpaRepository<Parceiro,Long> {//CrudRepository<FormaPagamento, Long> {

    //Tira o controle transacional, deixando a consulta mais rápida, pois tira o lokking do banco.
    @Transactional(readOnly = true)
    Parceiro findByEmail(String email);
}
