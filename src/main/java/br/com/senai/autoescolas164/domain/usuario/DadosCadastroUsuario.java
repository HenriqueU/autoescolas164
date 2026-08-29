package br.com.senai.autoescolas164.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank
        String login,

        @NotBlank
        String senha,

        @NotBlank
        Role perfil
) {

    public DadosCadastroUsuario(String login, String senha, Role perfil) {
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
