package br.com.senai.autoescolas164.adapter.in.controller.request.usuario;

import br.com.senai.autoescolas164.shared.vo.enums.Role;

public record DadosAtualizacaoUsuario(
        Long id,
        String login,
        String senha,
        Role perfil
) {

}
