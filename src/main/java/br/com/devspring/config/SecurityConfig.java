package br.com.devspring.config;

import br.com.devspring.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

//@EnableGlobalMethodSecurity(prePostEnabled = true) //necessário para ativar @PreAuthorize configurado no resource.
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //Obs: verificar se injetando a Interface UserDetailService o Spring já encontra a classe CustomUserDetailService
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private Environment env;//Específico para utilização do banco H2 na URL

    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**",
            "/formaspagamento/**",
            "/movimentacaoFinanceira/**"
    };

    private static final String[] PUBLIC_MATCHERS_GET = {
            "/formaspagamento/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Específico para utilização do banco H2 na URL
        if(Arrays.asList(env.getActiveProfiles()).contains("test")){
            http.headers().frameOptions().disable();
        }

        http.cors();
        http.authorizeRequests()
                //.antMatchers(HttpMethod.GET,PUBLIC_MATCHERS_GET).permitAll() //Permite as Urls
                .antMatchers(PUBLIC_MATCHERS).permitAll() //Permite as Urls
                .anyRequest().authenticated() //Para as demais URLs pede authenticação
                .and()
                .csrf().disable();//Necessário desabilitar somente ambiente de homologação para testar autenticação por roles(Perfil).
                //Ou caso a aplicação seja stateless, ou seja, não trabalha com armazenagem de sessoes.
        //Assegura que nao serão geradas sessoes.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder());
    }

    /* A permissão dee cors é necessária para que Requisições vindas de outro servidor(varias fontes) (Ex: o Front-End) possam funcionar,
    caso contrario receberão erro de Cors
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        /*CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(List.of(
                HttpMethod.GET.name(),
                HttpMethod.PUT.name(),
                HttpMethod.POST.name(),
                HttpMethod.DELETE.name()
        ));*/

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    //Objeto que pode ser injetado em qualquer classe do sistema
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /* Os dois métodos abaixo trata de autenticação de usuários do Cusro Spring Boot Essencials, mas naõ consegui colocar para funcionar
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //super.configure(http);
        http.authorizeRequests()
                .anyRequest()//.permitAll();
                .authenticated()
                .and()
                .httpBasic(); //existe outras formar de autenticar formlogin...
                //.and()
                //.csrf().disable();//Necessário desabilitar somente ambiente de homologação para testar autenticação por roles(Perfil).
    }

    //Autenticação de usuários
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }*/

    /*
    OS DOIS MÉTODOS ABAIXO FORAM UTILIZADOS NA TENTATIVA DE AUTORIZAR USUÁRIOS EM MEMÓRIA.

    //responsável por realizar a autenticação antes de acessar o EndPoint(resources). NAO FUNCIONOU
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //Atenticação em memoria
        auth.inMemoryAuthentication()
                .withUser("willian").password("devdojo").roles("USER")
                .and()
                .withUser("admin").password("devdojo").roles("USER","ADMIN");

    }


    //Peguei da documentação do SPING, mas NAO FUNCIONOU
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withUsername("alison")
                        .password("123456")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }*/
}
