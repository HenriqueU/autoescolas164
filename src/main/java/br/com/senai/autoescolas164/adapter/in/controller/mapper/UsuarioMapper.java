package br.com.senai.autoescolas164.adapter.in.controller.mapper;

import br.com.senai.autoescolas164.adapter.in.controller.request.usuario.DadosCadastroUsuario;
import br.com.senai.autoescolas164.adapter.in.controller.response.usuario.DadosDetalhamentoUsuario;
import br.com.senai.autoescolas164.adapter.in.controller.response.usuario.DadosListagemUsuario;
import br.com.senai.autoescolas164.application.core.domain.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {
    public Usuario toDomain(DadosCadastroUsuario dados) {
        return new Usuario(
                dados.getLogin(),
                dados.getSenha(),
                dados.getPerfil()
        );
    }

    public DadosDetalhamentoUsuario toDetailDto(Usuario usuario) {
        return new DadosDetalhamentoUsuario(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getPerfil()
        );
    }

    public DadosListagemUsuario toListDto(Usuario usuario) {
        return new DadosListagemUsuario(
                usuario.getUsername(),
                usuario.getPerfil()
        );
    }
}
