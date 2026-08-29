package br.com.senai.autoescolas164.domain.agenda.validacao;

import br.com.senai.autoescolas164.domain.agenda.DadosAgendamento;
import br.com.senai.autoescolas164.domain.agenda.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamento {

    @Override
    public void validar(DadosAgendamento dados) {
        LocalDateTime agendada = dados.dataHora();

        boolean domingo = agendada.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean preAbertura = agendada.getHour() < 7;
        boolean posFechamento = agendada.getHour() > (21 - 1);

        if (domingo || preAbertura || posFechamento) {
            throw new ValidacaoException("Tentativa de agendamento fora do horário de funcionamento!");
        }
    }
}
