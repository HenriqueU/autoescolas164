package br.com.senai.autoescolas164.adapter.in.controller.response.usuario;

import br.com.senai.autoescolas164.application.core.domain.Usuario;
import br.com.senai.autoescolas164.shared.vo.enums.Role;

public record DadosListagemUsuario(String login, Role perfil) {

    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getLogin(), usuario.getPerfil());
    }

}
