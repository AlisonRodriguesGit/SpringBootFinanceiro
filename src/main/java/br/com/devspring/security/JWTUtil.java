package br.com.devspring.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component //Pode ser injetado em outras classe.
public class JWTUtil {

    @Value("${jwt.secret}") //Pega a variável de ambiente localizada no 'application.properties.
    private String secret;
    @Value("${jwt.expiration}") //Pega a variável de ambiente localizada no 'application.properties.
    private Long expiration;

    public String generateToken(String username){
        return Jwts.builder() //cria a chave JWT
                .setSubject(username) //usuario
                .setExpiration(new Date(System.currentTimeMillis()+expiration)) //tempo atual + qtd de tempo para expirar
                .signWith(SignatureAlgorithm.HS512, secret.getBytes()) //Informa o algoritmo de criptografia e a chave(secret) que será utilizada.
                .compact();
    }

}
