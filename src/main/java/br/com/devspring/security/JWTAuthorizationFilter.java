package br.com.devspring.security;

import br.com.devspring.resources.utils.URL;
import br.com.devspring.services.CustomUserDetailService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private CustomUserDetailService customUserDetailService;

    private JWTUtil jwtUtil;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, CustomUserDetailService customUserDetailService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.customUserDetailService = customUserDetailService;
    }


    /*método que intercepta a requisição  */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //Pega a chave de nome 'Authorization'
        String header = URL.decodeParam(request.getQueryString());
        //header = request.getHeader("Authorization"); //não sei pq não funciona
        if (header != null) {
            int posicaoInicial = header.indexOf("Bearer");
            int posicaoFinal = header.indexOf("&", posicaoInicial);
            if (posicaoFinal != -1)
                header = header.substring(posicaoInicial, posicaoFinal);
            else if (posicaoInicial != -1)
                header = header.substring(posicaoInicial);
        }

        //Se não for vazia e começar com 'Bearer '
        if (header != null && header.startsWith("Bearer ")) {
            //passa o request e a chave 'Authorization' para validação
            UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));
            //se retornar diferente de null é pq a chave informada está correta.
            if (auth != null) {
                //função que libera o acesso da Requisição.
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        //informando que ele pode continuar com a requisição.
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        //if o token passado é válido
        if (jwtUtil.tokenValido(token)) {
            //recurepa o 'username' atraves do token
            String username = jwtUtil.getUsername(token);
            //cria um UserDetail a partir do servico 'customUserDetailService'
            UserDetails user = customUserDetailService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }
        return null;
    }
}
