package br.com.devspring.resource;

import br.com.devspring.domain.FinanceiroUser;
import br.com.devspring.repository.FinancerioUserRepository;
import br.com.devspring.services.FinanceiroUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.GET;

//cria alguams config. para acessar os endPoints
//quando iniciar pegar uma porta aleatória, para que se a aplicação estiver rodando não dê erro por conta da porta.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//Necessário para utilizar o MocMVC
@AutoConfigureMockMvc
//@WebMvcTest
public class FinanceiroUserResourceTest {

    @Autowired
    private TestRestTemplate restTemplate; //???
    @LocalServerPort //saber qual porta está sendo utilizada
    private int port;
    @MockBean
    //Simula o repositório (ele não injeta de fato o repositorio, cria um mock(exemplo, objeto falso) do repositorio, onde nos informamos qual o comportamento do repo)
    private FinancerioUserRepository financerioUserRepository;
    @MockBean
    private FinanceiroUserService financeiroUserService;
    @Autowired //Pode substituir o restTemplate.
    private MockMvc mockMvc;

    //criaçao dos Header p/ teste.
    private HttpEntity<Void> protectedHeader; //Usuario comum
    private HttpEntity<Void> adminHeader; //Usuario admin
    private HttpEntity<Void> wrongHeader; //Usuario sem acesso.

    @BeforeEach
    public void configProtectedHeaders() {
        //{"userName": "Alison","password": "dev"}
        String str = "{\"userName\": \"Alison\",\"password\": \"dev\"}";
        HttpHeaders headers = restTemplate.postForEntity("/login", str, String.class).getHeaders();
        this.protectedHeader = new HttpEntity<>(headers);
    }

    @BeforeEach
    public void configAdminHeaders() {
        //{"userName": "Joao","password": "123456"}
        String str = "{\"userName\": \"Joao\",\"password\": \"123456\"}";
        HttpHeaders headers = restTemplate.postForEntity("/login", str, String.class).getHeaders();
        this.protectedHeader = new HttpEntity<>(headers);
    }

    @BeforeEach
    public void configWrongHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "11111");
        this.wrongHeader = new HttpEntity<>(headers);
    }

    @BeforeEach //Antes de executar cada teste
    public void setup() {
        FinanceiroUser user = new FinanceiroUser("admin", "123456", "admin", "admin@gmail.com");
        BDDMockito.when(financerioUserRepository.findByUserName("admin")).thenReturn(Optional.ofNullable(user));
    }

    @Test
    public void listFinanceiroUserWhenTokenIsIncorrectShouldReturnStatusCode403() {
        when(this.financeiroUserService
                .findById(1L))
                .thenReturn(new FinanceiroUser("Joao","123456","Joao","joao@gmail.com"));
        ResponseEntity<String> response = restTemplate.exchange("/usuarios/{id}", GET, wrongHeader, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(403);
    }
}
