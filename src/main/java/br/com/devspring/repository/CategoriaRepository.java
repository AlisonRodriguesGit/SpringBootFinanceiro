package br.com.devspring.repository;

import br.com.devspring.domain.Categoria;
import br.com.devspring.domain.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*Exemplo de utilização:
localhost:8080/formaspagamento?sort=name,asc&sort=xxxx,desc
localhost:8080/formaspagamento?page=1
 */
@Repository
//Trabalhar com paginação                       //Contem já alguns métodos de consulta. JpaRepository<FormaPagamento, Long>
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {//CrudRepository<FormaPagamento, Long> {
    List<Categoria> findByNameIgnoreCaseContaining(String name);
}
