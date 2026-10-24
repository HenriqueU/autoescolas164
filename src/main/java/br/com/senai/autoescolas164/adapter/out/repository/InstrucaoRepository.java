package br.com.senai.autoescolas164.adapter.out.repository;

import br.com.senai.autoescolas164.application.core.domain.Instrucao;
import br.com.senai.autoescolas164.application.core.domain.Instrutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface InstrucaoRepository {
    boolean existsByInstrutorIdAndDataHora(Long idInstrutor, LocalDateTime DataHora);
    boolean existsByAlunoIdAndDataHoraBetween(Long id, LocalDateTime inicio, LocalDateTime fim);
    Instrucao save(Instrucao instrucao);
    Optional<Instrucao> findById(Long id);
    Page<Instrucao> findAllByAtivoTrue(Pageable paginacao);
}
