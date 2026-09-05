package br.com.senai.autoescolas164.application.core.specs;

import br.com.senai.autoescolas164.adapter.in.controller.request.instrucao.DadosAgendamento;

public interface ValidadorAgendamento {
    void validar(DadosAgendamento dados);
}
