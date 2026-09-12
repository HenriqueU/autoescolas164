package br.com.senai.autoescolas164.adapter.out.repository;

import br.com.senai.autoescolas164.application.core.domain.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository {

    Page<Aluno> findAllByAtivoTrue(Pageable paginacao);

    boolean existsByIdAndAtivoFalse(Long id);

    Aluno save(Aluno aluno);

    Optional<Aluno> findById(Long id);

    boolean existsById(Long id);

    Aluno getReferenceById(Long id);
}