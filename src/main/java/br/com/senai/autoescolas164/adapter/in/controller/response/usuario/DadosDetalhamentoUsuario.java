package br.com.senai.autoescolas164.adapter.in.controller.response.usuario;

import br.com.senai.autoescolas164.adapter.out.repository.entity.UsuarioEntity;
import br.com.senai.autoescolas164.application.core.domain.Usuario;
import br.com.senai.autoescolas164.shared.vo.enums.Role;

public record DadosDetalhamentoUsuario(Long id, String login, String senha, Role perfil) {

    public DadosDetalhamentoUsuario(UsuarioEntity usuario) {
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getPerfil()
        );
    }
}
