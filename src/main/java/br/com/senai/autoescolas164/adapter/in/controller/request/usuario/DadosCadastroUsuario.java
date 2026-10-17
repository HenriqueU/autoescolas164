package br.com.senai.autoescolas164.adapter.in.controller.request.usuario;

import br.com.senai.autoescolas164.shared.vo.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public record DadosCadastroUsuario(
        @NotBlank
        String login,

        @NotBlank
        String senha,

        @NotNull
        Role perfil
) {

    public DadosCadastroUsuario(String login, String senha, Role perfil) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        senha = encoder.encode(senha);

        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public String getLogin() {
        return login;
    }
    public String getSenha() { return senha; }
    public Role getPerfil() {
        return perfil;
    }
}
