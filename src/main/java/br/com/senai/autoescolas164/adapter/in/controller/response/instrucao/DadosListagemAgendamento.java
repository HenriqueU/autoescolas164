package br.com.senai.autoescolas164.adapter.in.controller.response.instrucao;

import br.com.senai.autoescolas164.adapter.out.repository.entity.AlunoEntity;
import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrutorEntity;

import java.time.LocalDateTime;

public record DadosListagemAgendamento(
        AlunoEntity aluno,
        InstrutorEntity instrutor,
        LocalDateTime dataHora
) {
}
