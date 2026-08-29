package br.com.senai.autoescolas164.domain.agenda;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface InstrucaoRepository extends JpaRepository<Instrucao, Long> {
    boolean existsByInstrutorIdAndDataHora(Long idInstrutor, LocalDateTime DataHora);
    boolean existsByAlunoIdAndDataHoraBetween(Long id, LocalDateTime inicio, LocalDateTime fim);
}
