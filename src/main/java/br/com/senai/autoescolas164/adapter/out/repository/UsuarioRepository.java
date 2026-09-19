package br.com.senai.autoescolas164.adapter.out.repository;

import br.com.senai.autoescolas164.application.core.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository {

    UserDetails findByLogin(String login);

    /*Page<Usuario> findAllLogin(Pageable paginacao);*/

    Usuario save(Usuario save);

    Optional<Usuario> findById(Long id);
}