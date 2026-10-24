package br.com.senai.autoescolas164.adapter.out.repository.persistence;

import br.com.senai.autoescolas164.adapter.out.repository.entity.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {
    UserDetails findByLogin(String login);
    /*Page<UsuarioEntity> findAllLogin(Pageable paginacao);*/
}
