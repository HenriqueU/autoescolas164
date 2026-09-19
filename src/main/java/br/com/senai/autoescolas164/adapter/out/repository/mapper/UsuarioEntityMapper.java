package br.com.senai.autoescolas164.adapter.out.repository.mapper;

import br.com.senai.autoescolas164.adapter.out.repository.entity.UsuarioEntity;
import br.com.senai.autoescolas164.application.core.domain.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioEntityMapper {
    public UsuarioEntity toEntity(Usuario domain) {
        return new UsuarioEntity(
                domain.getId(),
                domain.getUsername(),
                domain.getPassword(),
                domain.getPerfil(),
                domain.isEnabled()
        );
    }

    public Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(
                entity.getLogin(),
                entity.getSenha(),
                entity.getPerfil()
        );
    }
}
