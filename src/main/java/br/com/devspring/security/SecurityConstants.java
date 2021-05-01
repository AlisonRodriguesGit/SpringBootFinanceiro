package br.com.devspring.security;

/*Não está sendo utilizada, mas é uma outra maneira de criar variáveis para criação do token*/
public class SecurityConstants {

    //Authorization(Header) Bearer(Value) Hash(asdasgss13513)

    static final String SECRET = "FinanceiroToken"; //Chave para geração do Token(Assinatura do Token)
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    //Possivel informar uma URL para o usuário se logar, ou seja, essa URL não precisará de autenticação.
    static final String SIGN_UP_URL = "/user/sing-up"; //Exemplo de url
    static final long EXPIRATION_TIME = 60000L; //Token será válido por 1 mim em milisegundos;
    //static final long EXPIRATION_TIME = 86400000L; //Token será válido por 1 dia em milisegundos

    /*Convert um dia em Milisegundos;
    public static void main(String[] args) {
        System.out.println(TimeUnit.MILLISECONDS.convert(1,TimeUnit.DAYS));
    }*/



}
