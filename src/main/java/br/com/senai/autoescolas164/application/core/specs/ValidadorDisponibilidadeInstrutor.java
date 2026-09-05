package br.com.senai.autoescolas164.application.core.specs;

import br.com.senai.autoescolas164.adapter.in.controller.request.instrucao.DadosAgendamento;
import br.com.senai.autoescolas164.adapter.out.repository.InstrucaoRepository;
import br.com.senai.autoescolas164.exception.type.ValidacaoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor //Precisa checar informações no banco de dados (Necessário para injeção do repositório)
public class ValidadorDisponibilidadeInstrutor implements ValidadorAgendamento {
    private final InstrucaoRepository repository;

    @Override
    public void validar(DadosAgendamento dados) {
        boolean ocupado = repository.existsByInstrutorIdAndDataHora(
                dados.idInstrutor(),
                dados.dataHora()
        );

        if (ocupado) {
            throw new ValidacaoException("Instrutor ocupado na data e hora informada!");
        }
    }
}
