package br.com.devspring.security;

import br.com.devspring.domain.enums.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSpringSercurity implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSpringSercurity() {
    }

    public UserSpringSercurity(Long id, String email, String senha, Set<Perfil> authorities) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        //Convertendo uma lista de Perfil para uma Coleção de GrantedAuthority
        this.authorities = authorities.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
    }

    public Long getId(){
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override // A conta não está bloqueada ?
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override // as credencias não estão experiradas?
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override  //usuário está ativo?
    public boolean isEnabled() {
        return true;
    }
}
