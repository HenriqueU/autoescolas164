package br.com.senai.autoescolas164.application.core.domain;

import br.com.senai.autoescolas164.adapter.in.controller.request.usuario.DadosAtualizacaoUsuario;
import br.com.senai.autoescolas164.adapter.in.controller.request.usuario.DadosCadastroUsuario;
import br.com.senai.autoescolas164.shared.vo.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class Usuario implements UserDetails {

    private Long id;
    private String login;
    private String senha;
    private Role perfil; //Informa se o Usuário é "comum" (USER) ou "administrador" (ADMIN)
    private boolean ativo = true;

    public Usuario() {
    }

    public Usuario(String login, String senha, Role perfil) {
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public void atualizar(String login, String senha, Role perfil) {
        if (login != null && !login.isBlank()) {
            this.login = login;
        }
        if (senha != null && !senha.isBlank()) {
            this.senha = senha;
        }
        if (perfil != null && !login.isBlank()) {
            this.perfil = perfil;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + perfil.name()));
    }

    public Long getId() { return id; }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    public Role getPerfil() { return perfil; }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void excluir() {
        this.ativo = true;
    }
}
