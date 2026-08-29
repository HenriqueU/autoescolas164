package br.com.senai.autoescolas164.domain.usuario;

public record DadosListagemUsuario(String login, Role perfil) {

    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getLogin(), usuario.getPerfil());
    }
}
