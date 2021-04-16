package br.com.devspring;

import br.com.devspring.domain.FormaPagamento;
import br.com.devspring.domain.MovimentacaoFinanceira;
import br.com.devspring.repository.FormaPagamentoRepository;
import br.com.devspring.repository.MovimentacaoFinanceiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class SpringBootFinanceiroApplication implements CommandLineRunner { //inteface permite executar comandos na inicialização do sistema.

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFinanceiroApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
