package br.com.senai.autoescolas164.application.core.specs;

import br.com.senai.autoescolas164.adapter.in.controller.request.instrucao.DadosAgendamento;
import br.com.senai.autoescolas164.exception.type.ValidacaoException;
import br.com.senai.autoescolas164.adapter.out.repository.InstrutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidadorInstrutorAtivo implements ValidadorAgendamento{
    private final InstrutorRepository instrutorRepository;

    @Override
    public void validar(DadosAgendamento dados) {
        if (instrutorRepository.existsByIdAndAtivoFalse(dados.idInstrutor())) {
            throw new ValidacaoException("Instrutor não está ativo!");
        }
    }
}