package br.com.senai.autoescolas164.domain.agenda.validacao;

import br.com.senai.autoescolas164.domain.agenda.DadosAgendamento;
import br.com.senai.autoescolas164.domain.agenda.ValidacaoException;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamento{

    @Override
    public void validar(DadosAgendamento dados) {
        LocalDateTime agendada = dados.dataHora();
        LocalDateTime agora = LocalDateTime.now();

        long antecedencia = Duration.between(agora, agendada).toMinutes();

        if (antecedencia < 60) {
            throw new ValidacaoException("Antecedência mínima de 1 hora para agendamento!");
        }
    }
}
