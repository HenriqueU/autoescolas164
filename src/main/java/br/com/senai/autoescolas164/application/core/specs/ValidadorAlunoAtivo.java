package br.com.senai.autoescolas164.application.core.specs;

import br.com.senai.autoescolas164.adapter.in.controller.request.instrucao.DadosAgendamento;
import br.com.senai.autoescolas164.exception.type.ValidacaoException;
import br.com.senai.autoescolas164.adapter.out.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidadorAlunoAtivo implements ValidadorAgendamento{
    private final AlunoRepository alunoRepository;

    @Override
    public void validar(DadosAgendamento dados) {
        if (alunoRepository.existsByIdAndAtivoFalse(dados.idAluno())) {
            throw new ValidacaoException("Alunos inativos não podem agendar instruções!");
        }
    }
}
