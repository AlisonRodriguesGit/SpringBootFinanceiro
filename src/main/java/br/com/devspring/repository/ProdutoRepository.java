package br.com.devspring.repository;

import br.com.devspring.domain.Categoria;
import br.com.devspring.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*Exemplo de utilização:
localhost:8080/formaspagamento?sort=name,asc&sort=xxxx,desc
localhost:8080/formaspagamento?page=1
 */
@Repository
//Trabalhar com paginação                       //Contem já alguns métodos de consulta. JpaRepository<FormaPagamento, Long>
public interface ProdutoRepository extends JpaRepository<Produto,Long> {//CrudRepository<FormaPagamento, Long> {
    List<Produto> findByNameIgnoreCaseContaining(String name);

    //@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categoria cat WHERE obj.name LIKE %:name% AND cat IN :categorias")
    //Page<Produto> search(@Param("name") String name, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
    //Tira o controle transacional, deixando a consulta mais rápida, pois tira o lokking do banco.
    @Transactional(readOnly = true)
    Page<Produto> findDistinctByNameContainingAndAndCategoriaIn(String name,List<Categoria> categorias, Pageable pageRequest);
}
