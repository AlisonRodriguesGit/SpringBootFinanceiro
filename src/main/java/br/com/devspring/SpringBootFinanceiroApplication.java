package br.com.devspring;

import br.com.devspring.domain.FormaPagamento;
import br.com.devspring.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootFinanceiroApplication implements CommandLineRunner { //inteface permite executar comandos na inicialização do sistema.

    @Autowired
    private FormaPagamentoRepository formaPagamentoDAO;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFinanceiroApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        FormaPagamento formaPagamento1 = new FormaPagamento("Dinheiro");
        FormaPagamento formaPagamento2 = new FormaPagamento("Cartão");

        formaPagamentoDAO.saveAll(Arrays.asList(formaPagamento1,formaPagamento2));

    }
}
