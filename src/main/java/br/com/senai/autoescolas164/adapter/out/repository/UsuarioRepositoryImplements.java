package br.com.senai.autoescolas164.adapter.out.repository;

import br.com.senai.autoescolas164.adapter.out.repository.entity.UsuarioEntity;
import br.com.senai.autoescolas164.adapter.out.repository.mapper.UsuarioEntityMapper;
import br.com.senai.autoescolas164.adapter.out.repository.persistence.UsuarioJpaRepository;
import br.com.senai.autoescolas164.application.core.domain.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

//Adaptadores
@Component
@RequiredArgsConstructor
public class UsuarioRepositoryImplements implements UsuarioRepository {
    private final UsuarioJpaRepository jpaRepository;
    private final UsuarioEntityMapper entityMapper;

    @Override
    public UserDetails findByLogin(String login) {
        return jpaRepository.findByLogin(login);
    }

    /*@Override
    public Page<Usuario> findAllLogin(Pageable paginacao) {
        return jpaRepository.findAllLogin(paginacao).map(entityMapper::toDomain);
    }*/

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = entityMapper.toEntity(usuario);
        UsuarioEntity salvo = jpaRepository.save(entity);
        return entityMapper.toDomain(salvo);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return jpaRepository.findById(id).map(entityMapper::toDomain);
    }
}
