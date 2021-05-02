package br.com.devspring.repository;

import br.com.devspring.domain.FinanceiroUser;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class) //Classe SpringRunner é responsável por rodas os testes
@DataJpaTest //Varias configurações para realizar teste com o banco
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FinanceiroUserRepositoryTest {

    @Autowired
    private FinancerioUserRepository financerioUserRepository;
    @Rule
    public ExpectedException thrown = ExpectedException.none(); //Informamos as exceções esperadas e se não ocorrer teremos o teste falho.

    @Test  //metodo create deve persistir os dados
    public void createShouldPersistData(){
        //cria um usuário
        FinanceiroUser user = new FinanceiroUser("Joao","123456","Joao","joao@gmail.com");
        //salva
        this.financerioUserRepository.save(user);
        //verifica o resultado (afirma que...)
        assertThat(user.getId()).isNotNull(); // se gerou algum id
        //verificando se o restante dos dados estão iguais ao passado na criação do objeto.
        assertThat(user.getUserName()).isEqualTo("Joao");
        assertThat(user.getSenha()).isEqualTo("123456");
        assertThat(user.getName()).isEqualTo("Joao");
        assertThat(user.getEmail()).isEqualTo("joao@gmail.com");
    }

    @Test
    public void deleteShouldRemoveData(){
        //cria um usuário
        FinanceiroUser user = new FinanceiroUser("Joao","123456","Joao","joao@gmail.com");
        //salva
        this.financerioUserRepository.save(user);
        //deleta
        financerioUserRepository.delete(user);
        //verifica se ao consultar no banco está vazio.
        assertThat(financerioUserRepository.findById(user.getId())).isEmpty();
    }

    @Test
    public void updateShouldChangeAndPersistData(){
        //cria um usuário
        FinanceiroUser user1 = new FinanceiroUser("Joao","123456","Joao","joao@gmail.com");
        //salva
        this.financerioUserRepository.save(user1);
        user1.setUserName("Fulano");
        user1.setSenha("654321");
        //realiza o update
        this.financerioUserRepository.save(user1);
        Optional<FinanceiroUser> userAlterado = this.financerioUserRepository.findById(user1.getId());
        //verifica se ao consultar no banco está vazio.
        assertThat(userAlterado.get().getUserName()).isEqualTo("Fulano");
        assertThat(userAlterado.get().getSenha()).isEqualTo("654321");
    }

    @Test
    public void findByUserNameShouldGetFindUser(){
        //cria um usuário
        FinanceiroUser user1 = new FinanceiroUser("Joao","123456","Joao","joao@gmail.com");
        FinanceiroUser user2 = new FinanceiroUser("Alison","123456","Alison","alison@gmail.com");
        //salva
        this.financerioUserRepository.saveAll(Arrays.asList(user1,user2));
        //realiza a busco por nome do usuario
        Optional<FinanceiroUser> usuario = this.financerioUserRepository.findByUserName(user1.getUserName());
        //verificando se o restante dos dados estão iguais ao passado na criação do objeto.
        assertThat(usuario.get().getUserName()).isEqualTo("Joao");
        assertThat(usuario.get().getSenha()).isEqualTo("123456");
        assertThat(usuario.get().getName()).isEqualTo("Joao");
        assertThat(usuario.get().getEmail()).isEqualTo("joao@gmail.com");
    }

    //Teste para validar se não foi informado nome do usuário
    @Test
    public void createWhenNameIsNullShouldThrowConstraintViolationException(){
        //esperace que o erro retornado seja ConstraintViolationException
        thrown.expect(ConstraintViolationException.class);
        //esperace que a mensagem seja "Nome do usuário deve ser informado!"
        thrown.expectMessage("Nome do ");
        //salva um FinanceiroUser sem o nome do usuario
        this.financerioUserRepository.save(new FinanceiroUser("","teste", "teste","teste@gmail.com"));
    }

    //Teste para validar se não foi informado email do usuário
    @Test
    public void createWhenEmailIsNullShouldThrowConstraintViolationException(){
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("Email do usuário deve ser informado!");
        this.financerioUserRepository.save(new FinanceiroUser("teste","teste", "teste",""));
    }

    //Teste para validar se não foi informado email do usuário
    @Test
    public void createWhenEmailNotValidShouldThrowConstraintViolationException(){
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("Digite um email válido!");
        this.financerioUserRepository.save(new FinanceiroUser("teste","teste", "teste","email"));
    }

}
