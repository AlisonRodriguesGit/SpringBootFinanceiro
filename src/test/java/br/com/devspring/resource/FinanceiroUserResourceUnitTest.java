package br.com.devspring.resource;

import br.com.devspring.resources.FinanceiroUserResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

@WebMvcTest
//@SpringBootTest
public class FinanceiroUserResourceUnitTest {

    @Autowired
    private FinanceiroUserResource financeiroUserResource;

    @BeforeEach
    public void setup(){

    }

    @Test
    //private void deveRetornarSucesso_QuandoBuscarFilme(){
    private void listFinanceiroUserShouldStatusCode200(){

    }
}
