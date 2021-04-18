package br.com.devspring.config.Profiles;

import br.com.devspring.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    //recupera o valor da chave do arquivo 'applicantion-dev.properties'
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase(){

        if (!"create".contains(strategy))
            return false;

        dbService.instantiateTestDataBase();
        return true;
    }
}
