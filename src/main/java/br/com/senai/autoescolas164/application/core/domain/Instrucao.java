package br.com.senai.autoescolas164.application.core.domain;

import br.com.senai.autoescolas164.adapter.in.controller.request.instrucao.DadosAgendamento;
import br.com.senai.autoescolas164.adapter.out.repository.entity.AlunoEntity;
import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrutorEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class Instrucao {

    private Long id;
    private AlunoEntity aluno;
    private InstrutorEntity instrutor;
    private LocalDateTime dataHora;
    private boolean ativo = true;

    public Instrucao(
            Long id,
            AlunoEntity aluno,
            InstrutorEntity instrutor,
            LocalDateTime dataHora,
            boolean ativo
    ) {
        this.id = id;
        this.aluno = aluno;
        this.instrutor = instrutor;
        this.dataHora = dataHora;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public AlunoEntity getAluno() {
        return aluno;
    }

    public InstrutorEntity getInstrutor() {
        return instrutor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void excluir() {
        this.ativo = false;
    }
}
