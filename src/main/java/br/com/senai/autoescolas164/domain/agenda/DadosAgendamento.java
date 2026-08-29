package br.com.senai.autoescolas164.domain.agenda;

import br.com.senai.autoescolas164.domain.instrutor.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamento(
        @NotNull
        @JsonProperty("id_aluno") //Informa que a propriedade "id_aluno" no FrontEnd é a propriedade "idAluno" no BackEnd
        Long idAluno,

        @JsonProperty("id_instrutor") //Informa que a propriedade "id_instrutor" no FrontEnd é a propriedade "idInstrutor" no BackEnd
        Long idInstrutor,

        Especialidade especialidade,

        @NotNull
        @Future //Impede que seja registrado um agendamento em uma data passada
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
        @JsonProperty("data_hora") //Informa que a propriedade "data_hora" no FrontEnd é a propriedade "dataHora" no BackEnd
        LocalDateTime dataHora
) {
}
