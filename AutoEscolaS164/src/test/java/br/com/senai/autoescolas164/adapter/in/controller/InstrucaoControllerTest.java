package br.com.senai.autoescolas164.adapter.in.controller;

import br.com.senai.autoescolas164.adapter.in.controller.request.instrucao.DadosAgendamento;
import br.com.senai.autoescolas164.adapter.in.controller.response.instrucao.DadosDetalhamentoAgendamento;
import br.com.senai.autoescolas164.application.service.AgendaDeInstrucoes;
import br.com.senai.autoescolas164.shared.vo.enumeration.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class InstrucaoControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    JacksonTester<DadosAgendamento> dadosAgendamentoJson;

    @Autowired
    JacksonTester<DadosDetalhamentoAgendamento> dadosDetalhamentoAgendamentoJson;

    @MockitoBean
    AgendaDeInstrucoes agenda;

    @Test
    @DisplayName("Expectativa: retornar código 400 para informações inválidas")
    @WithMockUser
    void agendarInstrucaoCenario1() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(post("/instrucoes"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Expectativa: retornar código 200 para informações válidas e o json de resposta")
    @WithMockUser
    void agendarInstrucaoCenario2() throws Exception {
        LocalDateTime dataHora = LocalDateTime
                .now()
                .plusHours(2)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);

        Especialidade especialidade = Especialidade.CARROS;

        DadosDetalhamentoAgendamento dto = new DadosDetalhamentoAgendamento(
                null,
                "Aluno Teste",
                "Instrutor Teste",
                especialidade,
                dataHora
        );

        when(agenda.agendar(any())).thenReturn(dto);

        MockHttpServletResponse response = mockMvc
                .perform(post("/instrucoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAgendamentoJson.write(
                                new DadosAgendamento(
                                        1L,
                                        1L,
                                        especialidade,
                                        dataHora
                                )
                        ).getJson())
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        String jsonEsperado = dadosDetalhamentoAgendamentoJson.write(
                new DadosDetalhamentoAgendamento(
                        null,
                        "Aluno Teste",
                        "Instrutor Teste",
                        especialidade,
                        dataHora
                )
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}