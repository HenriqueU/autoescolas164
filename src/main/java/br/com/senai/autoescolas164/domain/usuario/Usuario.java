package br.com.senai.autoescolas164.domain.usuario;

import jakarta.persistence.*;
import jakarta.validation.Valid;
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

@Entity(name = "Usuario")
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    private String login;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role perfil; //Informa se o Usuário é "comum" (USER) ou "administrador" (ADMIN)

    private boolean ativo = true;

    public Usuario(DadosCadastroUsuario dados) {
        this.login = dados.getLogin();
        this.senha = dados.getSenha();
        this.perfil = dados.getPerfil();
    }

    public void atualizar(DadosAtualizacaoUsuario dados) {
        if (dados.login() != null && !dados.login().isBlank()) {
            this.login = dados.login();
        }
        if (dados.senha() != null && !dados.login().isBlank()) {
            this.senha = dados.senha();
        }
        if (dados.perfil() != null && !dados.login().isBlank()) {
            this.perfil = dados.perfil();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + perfil.name()));
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

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
