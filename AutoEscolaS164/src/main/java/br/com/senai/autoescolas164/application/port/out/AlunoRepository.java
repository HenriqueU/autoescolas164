package br.com.senai.autoescolas164.application.port.out;

import br.com.senai.autoescolas164.application.core.domain.Aluno;

public interface AlunoRepository {
    boolean existsByIdAndAtivoFalse(Long id);

    boolean existsById(Long id);

    Aluno getReferenceById(Long id);
}