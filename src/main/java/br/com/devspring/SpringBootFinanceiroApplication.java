package br.com.devspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SpringBootFinanceiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFinanceiroApplication.class, args);
    }

}
