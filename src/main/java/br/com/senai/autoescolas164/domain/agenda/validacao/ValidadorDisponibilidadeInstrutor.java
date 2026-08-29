package br.com.senai.autoescolas164.domain.agenda.validacao;

import br.com.senai.autoescolas164.domain.agenda.DadosAgendamento;
import br.com.senai.autoescolas164.domain.agenda.InstrucaoRepository;
import br.com.senai.autoescolas164.domain.agenda.ValidacaoException;
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
