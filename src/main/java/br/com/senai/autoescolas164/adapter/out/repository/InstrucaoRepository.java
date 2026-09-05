package br.com.senai.autoescolas164.adapter.out.repository;

import br.com.senai.autoescolas164.application.core.domain.Instrucao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface InstrucaoRepository extends JpaRepository<Instrucao, Long> {
    boolean existsByInstrutorIdAndDataHora(Long idInstrutor, LocalDateTime DataHora);
    boolean existsByAlunoIdAndDataHoraBetween(Long id, LocalDateTime inicio, LocalDateTime fim);
}
