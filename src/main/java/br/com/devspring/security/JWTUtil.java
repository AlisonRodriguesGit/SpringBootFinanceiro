package br.com.devspring.security;

import io.jsonwebtoken.Claims;
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

    public boolean tokenValido(String token) {
        //O Claims armazena as reenvidicaoes do token. No caso o usuario e o tempo de expiração.
        Claims claims = getClaims(token);
        if (claims != null){
             //Pega o nome do usuário do TOKEN.
             String username = claims.getSubject();
             //Pega a Data de expiração do TOKEN.
             Date expirationDate = claims.getExpiration();
             //Pega a Data Atual
             Date now = new Date(System.currentTimeMillis());
             //Se pegou o usuário e a hora atual está anterior(before) a hora de expiração, o Token está válido.
             if (username != null && expirationDate != null && now.before(expirationDate)){
                 return true;
             }
        }
        return false;
    }

    /*pega o claims(reivindicações: Usuário,Tempo de Expiração do token...)a partir do token. Caso token inválido retorna null*/
    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        }catch (Exception e){
            return null;
        }
    }

    public String getUsername(String token) {
        //O Claims armazena as reenvidicaoes do token. No caso o usuario e o tempo de expiração.
        Claims claims = getClaims(token);
        if (claims != null)
            return claims.getSubject(); //Pega o nome do usuário do TOKEN.
        return null;
    }
}
