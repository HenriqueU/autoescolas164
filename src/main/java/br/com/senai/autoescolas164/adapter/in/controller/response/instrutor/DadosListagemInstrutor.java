package br.com.senai.autoescolas164.adapter.in.controller.response.instrutor;

import br.com.senai.autoescolas164.shared.vo.enums.Especialidade;

public record DadosListagemInstrutor(Long id, String nome, String email, Especialidade especialidade) {
}
