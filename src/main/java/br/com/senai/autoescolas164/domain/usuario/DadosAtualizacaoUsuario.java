package br.com.senai.autoescolas164.domain.usuario;

public record DadosAtualizacaoUsuario(
        Long id,
        String login,
        String senha,
        Role perfil
) {

}
