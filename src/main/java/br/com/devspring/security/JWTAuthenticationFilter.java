package br.com.devspring.security;

import br.com.devspring.dto.CredenciaisDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/*Intercepta a requisição de Login(/login),verifica as credenciais, autenticar ou não , gerando um Token JWT.
 * Necessário implementar AuthenticationFailureHandler e injeta-la nesse classe para personalizar o retorno(403-Não Autorizado) para 401(Autenticado).
 * */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JWTUtil jwtUtil;

    private AuthenticationManager authenticationManager;

    //Injeção de dependencia via Construtor, ou seja, quando instanciar a classe deve passar no construtor os dois atributos.
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        //Injetando a classe responsável por alterar o Status Http do retorno p/ 401.
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override //Primeiro método a ser chamado. Tentativa de autenticação
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //Lendo a requisição (usuario e senha) e convertendo para o Objeto Financeiro Usuario.
        try {
            //Pega o request(requisiçao) e Convert em um FinanceiroUser para pegar usuário e senha.
            CredenciaisDTO user = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);

            UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

            //Passando a autenticação no authenticationManager, onde o próprio Spring chamará o método 'successfulAuthentication'
            Authentication auth = authenticationManager.authenticate(userToken);

            return auth;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override //Caso sucesso na autenticação. Esse método é chamado e gera-rá o TOKEN.
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //o objeto 'authResult' já contem as informações de login e senha recuperados no método de interceptação acima.
        //foi necessário converter para um objeto 'UserSpringSecurity' p/ acessar o 'User Name'
        String username = ((UserSpringSercurity) authResult.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        //Envia o retorno no cabeçalho.
        response.addHeader("Authorization", "Bearer " + token);
    }

    /* Classe responsável por mudar o retorno do erro HTTP para 401, pois por padrão retorna 403 Forbiden*/
    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().append(json());
        }

        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                    + "\"status\": 401, "
                    + "\"error\": \"Não autorizado\", "
                    + "\"message\": \"Usuário ou senha inválidos\", "
                    + "\"path\": \"/login\"}";
        }
    }
}
