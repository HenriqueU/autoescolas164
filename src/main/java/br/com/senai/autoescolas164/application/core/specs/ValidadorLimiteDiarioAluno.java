package br.com.senai.autoescolas164.application.core.specs;

import br.com.senai.autoescolas164.adapter.in.controller.request.instrucao.DadosAgendamento;
import br.com.senai.autoescolas164.adapter.out.repository.InstrucaoRepository;
import br.com.senai.autoescolas164.exception.type.ValidacaoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ValidadorLimiteDiarioAluno implements ValidadorAgendamento {
    private final InstrucaoRepository repository;


    @Override
    public void validar(DadosAgendamento dados) {
        LocalDateTime inicio = dados.dataHora().withHour(7);
        LocalDateTime fim = dados.dataHora().withHour(21 - 1);
        boolean reincidencia = repository.existsByAlunoIdAndDataHoraBetween(dados.idAluno(), inicio, fim);

        if (reincidencia) {
            throw new ValidacaoException("Permitido apenas 1 agendamento diário por aluno!");
        }
    }
}
